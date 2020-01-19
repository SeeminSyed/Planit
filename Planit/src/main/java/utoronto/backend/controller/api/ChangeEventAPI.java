package utoronto.backend.controller.api;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Map;

import com.sun.net.httpserver.*;

import org.json.JSONException;
import org.json.JSONObject;

import utoronto.backend.controller.business.utils.EventChange;
import utoronto.backend.controller.business.utils.JSONExchangeConverter;

public class ChangeEventAPI implements HttpHandler {

    private EventChange details = new EventChange();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String[] params = new String[] { "venue_id" };
        if (exchange.getRequestMethod().equals("GET")) {
            try {
                // extract the query params and its value
                Map<String, String> query = JSONExchangeConverter.convertFromGetToMap(exchange, params);
                String response = details.detailsEndPoint(query.get("venue_id"));
                if (response == null) {
                    // the info given is not proper, 4sq threw an error => 404
                    exchange.sendResponseHeaders(404, -1);
                }
                // send the response to the front end
                exchange.sendResponseHeaders(200, response.getBytes().length);
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (JSONException e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(400, -1);
            } catch (IOException e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(500, -1);
            }
        } else {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
    }
}
