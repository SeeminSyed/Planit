package utoronto.backend.controller.api;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.*;
import org.json.JSONException;
import org.json.JSONObject;
import utoronto.backend.model.service.DAOImpl;

public class TransportationAPI implements HttpHandler {

    private DAOImpl dao = new DAOImpl();

    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {

            int responseNumber = 500;
            String response = "";

            try {

                JSONObject jsonResponse = new JSONObject();
                String [] choices = dao.getTransportationMethods().split(",");
                
                jsonResponse.put("transportation", String.join(",", choices));
                responseNumber = 200;
                response = jsonResponse.toString();

            } catch (JSONException e) {

                responseNumber = 400;
                e.printStackTrace();


            } catch (Exception e) {

                responseNumber = 500;
                e.printStackTrace();

            }finally {

                exchange.sendResponseHeaders(responseNumber, response.getBytes().length);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

            }
        }
    }


}