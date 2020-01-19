package utoronto.backend.controller.api;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.*;
import org.json.JSONException;

import utoronto.backend.controller.business.utils.EventSearch;
import utoronto.backend.controller.business.utils.JSONExchangeConverter;

public class SearchAPI implements HttpHandler {

    private EventSearch explore = new EventSearch();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String sectionDefault = "topPicks", priceDefault = "1";
        try {
            if (exchange.getRequestMethod().equals("GET")) {
                // extract the query params and its value
                Map<String, String> params = JSONExchangeConverter.convertFromGetToMap(exchange,
                 new String[] {"query", "near", "section", "price"});// mapParamVal(exchange.getRequestURI().getQuery());
                // if section and price are not present (since they're optional)
                // add the default key, value
                if (!params.containsKey("section")) {
                    params.put("section", sectionDefault);
                }
                if (!params.containsKey("price")) {
                    params.put("price", priceDefault);
                }
                // send it to the explore end point
                String response = explore.exploreEndPoint(params.get("near"), params.get("query"),
                        params.get("section"), params.get("price"));
                if (response == null) {
                    // the info given is not proper, 4sq threw an error => 404
                    exchange.sendResponseHeaders(404, -1);
                    return;
                }
                // life's good \o/
                exchange.sendResponseHeaders(200, response.getBytes().length);
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(400, -1);
            return ;
        }
    }

    /*
    private static Map<String, String> mapParamVal(String query) {
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1 && (pair[0].equals("near") || pair[0].equals("query") || pair[0].equals("section")
                    || pair[0].equals("price"))) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }
    // Reference:
    // https://www.rgagnon.com/javadetails/java-get-url-parameters-using-jdk-http-server.html
    */
}
