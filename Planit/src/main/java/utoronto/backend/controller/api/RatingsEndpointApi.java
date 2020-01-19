package utoronto.backend.controller.api;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.*;

import org.json.JSONException;
import org.json.JSONObject;

import utoronto.backend.controller.business.utils.JSONExchangeConverter;
import utoronto.backend.model.service.insertdao.InsertDAO;

public class RatingsEndpointApi implements HttpHandler {
    // accessing the dao
    private InsertDAO DAO;

    public RatingsEndpointApi(InsertDAO dao) {
        this.DAO = dao;
    }

    public void handle(HttpExchange exchange) throws IOException {
        //To-do: add rating to database using InsertDAO
		if (exchange.getRequestMethod().equals("PUT")) {

            int responseNumber = 500;
            String response = "";
            try {
                JSONObject body = JSONExchangeConverter.convert(exchange);
                if (body.has("user_id") && body.has("attraction_id") && body.has("rating")) {
                    String user_id = body.getString("user_id");
                    String attraction_id = body.getString("attraction_id");
                    int rating = body.getInt("rating");
                    
                    DAO.insertRating(user_id, attraction_id, rating);
                    responseNumber = 200;

                } else {
                    responseNumber = 400;
                }
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
