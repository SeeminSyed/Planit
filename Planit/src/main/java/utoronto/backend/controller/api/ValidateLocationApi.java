package utoronto.backend.controller.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import utoronto.backend.controller.business.utils.JSONExchangeConverter;
import utoronto.backend.controller.business.validation.LocationValidationImpl;
import utoronto.backend.controller.business.validation.Validation;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Consumes the input and checks if the city is valid.
 */
public class ValidateLocationApi implements HttpHandler {

    Validation locationValidator = new LocationValidationImpl();

    public void handle(HttpExchange exchange) throws IOException {
        int responseNumber = 500;
        String response = "";

        if (exchange.getRequestMethod().equals("GET")) {
            try {
                // Convert input to JSON
//                JSONObject body = JSONExchangeConverter.convert(exchange);
                JSONObject body = JSONExchangeConverter.convertFromGetToJSON(exchange,
                        new String[] {"city", "country"});
                // Force input to have city and country
                if (body.has("city") && body.has("country")) {
                    String city = body.getString("city");
                    String country = body.getString("country");
                    // use ValidationImpl to validate city
                    boolean validCity = locationValidator.validateCity(city, country);
                    // Put response
                    JSONObject jsonResponse = new JSONObject();
                    jsonResponse.put("result", validCity);
                    response = jsonResponse.toString();
                    responseNumber = 200;

                } else {
                    responseNumber = 400;
                }
//            } catch (JSONException e) {
//                responseNumber = 400;
//                e.printStackTrace();
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
        } else {
            exchange.sendResponseHeaders(405, response.getBytes().length);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }


}