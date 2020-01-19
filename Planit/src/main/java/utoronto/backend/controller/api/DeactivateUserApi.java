package utoronto.backend.controller.api;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.*;

import org.json.JSONException;
import org.json.JSONObject;

import utoronto.backend.controller.business.utils.JSONExchangeConverter;
import utoronto.backend.model.service.updatedao.UpdateDAO;

public class DeactivateUserApi implements HttpHandler {
    // accessing the dao
    private UpdateDAO DAO;

    public DeactivateUserApi(UpdateDAO dao) {
        this.DAO = dao;
    }

    public void handle(HttpExchange exchange) throws IOException {
		if (exchange.getRequestMethod().equals("PUT")) {

            int responseNumber = 500;
            String response = "";
            try {
                JSONObject body = JSONExchangeConverter.convert(exchange);
                if (body.has("user_id")) {
                    String user_id = body.getString("user_id");
                    
                    //print values to console and inserts into database
                    System.out.println(user_id);

                    DAO.deactivateUser(user_id);
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