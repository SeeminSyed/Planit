package utoronto.backend.controller.api;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.*;

import org.json.JSONException;
import org.json.JSONObject;

import utoronto.backend.controller.business.utils.JSONExchangeConverter;
import utoronto.backend.model.service.insertdao.InsertDAO;

public class UserRegistrationApi implements HttpHandler {
    // accessing the dao
    private InsertDAO DAO;

    public UserRegistrationApi(InsertDAO dao) {
        this.DAO = dao;
    }

    public void handle(HttpExchange exchange) throws IOException {
        //To-do: add user info to database using InsertDAO
		if (exchange.getRequestMethod().equals("PUT")) {

            int responseNumber = 500;
            String response = "";
            try {
                JSONObject body = JSONExchangeConverter.convert(exchange);
                // check JSON body for the following requirements
                if (body.has("user_id") && body.has("username") && body.has("password") && body.has("email") && body.has("active")) {
                    String user_id = body.getString("user_id");
                    String username = body.getString("username");
                    String password = body.getString("password");
                    String email = body.getString("email");
                    boolean active = body.getBoolean("active");
                    // print out all the values to test, then insert into database
                    System.out.println(user_id);
                    System.out.println(username);
                    System.out.println(password);
                    System.out.println(email);
                    System.out.println(active);
                    DAO.insertUser(user_id, username, password, email, active);
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