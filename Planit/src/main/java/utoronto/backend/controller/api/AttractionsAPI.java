package utoronto.backend.controller.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utoronto.backend.Globals;
import utoronto.backend.controller.business.utils.JSONExchangeConverter;
import utoronto.backend.model.service.insertdao.InsertDAO;
import utoronto.backend.model.service.retrievedao.RetrieveDAO;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AttractionsAPI implements HttpHandler {

    private RetrieveDAO retrieveDAO;
    private InsertDAO insertDAO;
    public CloseableHttpClient client;

    public AttractionsAPI(RetrieveDAO retrieveDAO, InsertDAO insertDAO) {
        this.retrieveDAO = retrieveDAO;
        this.insertDAO = insertDAO;
        this.client = Globals.client.getConnectorObject();
    }

    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {
            handleGet(exchange);
        } else if (exchange.getRequestMethod().equals("POST")) {
            handlePost(exchange);
        } else {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        int responseNumber = 500;
        String response = "";

        try {
            JSONObject jsonResponse = new JSONObject();
            JSONArray choices = retrieveDAO.getAttraction();

            jsonResponse.put("attractions", choices.toString());
            response = jsonResponse.toString();
            responseNumber = 200;

        } catch (JSONException e) {

            responseNumber = 400;
            e.printStackTrace();


        } catch (Exception e) {

            responseNumber = 500;
            e.printStackTrace();

        } finally {
            exchange.sendResponseHeaders(responseNumber, response.getBytes().length);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }
    }


    private void handlePost(HttpExchange exchange) throws IOException {
        int responseNumber = 500;
        try {
                // call attractions api
                String uri = Globals.categoriesAPI;
                HttpGet request = new HttpGet(uri
                        + "?client_id=" + Globals.clientId
                        + "&client_secret=" + Globals.clientSecret
                        + "&v=" + Globals.version
                );
//                System.out.println(request.toString());
                request.addHeader("Content-Type", "application/json");

                // parse response
                CloseableHttpResponse eventResponse = client.execute(request);
                // Get HttpResponse Status
                HttpEntity entity = eventResponse.getEntity();
                if (entity == null) {
                    responseNumber = 400;
                    throw new JSONException("Foursquare public API not working.\n");
                }

                JSONObject data = new JSONObject(EntityUtils.toString(entity));
                eventResponse.close();

                JSONArray categories = data.getJSONObject("response").getJSONArray("categories");

                // get specific event details
                Map<String, String> attractions = new HashMap<>();
                for (int i = 0; i < categories.length(); ++i) {
                    String id = categories.getJSONObject(i).getString("id");
                    String name = categories.getJSONObject(i).getString("name");
                    // insert attractions into database
                    if (id != null && name != null) {
                        try {
                            insertDAO.insertAttraction(id, name);
                        } catch (SQLException e2) {
                            // do nothing
                        }
                    }
                }

                responseNumber = 200;
        } catch (JSONException e1) {
            responseNumber = 400;
        } finally {
            exchange.sendResponseHeaders(responseNumber, -1);
        }
    }


}