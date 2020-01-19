package utoronto.backend.controller.business.utils;

import com.sun.net.httpserver.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utility class used for converting exhanges directly to JSONObjects.
 */
public class JSONExchangeConverter {

    /**
     * 
     * @param exchange The Http exchange to convert to java
     * @return The converted JSONbject
     * @throws IOException
     * @throws JSONException
     * You can only do this once per exchange, as this conversion consumes
     * the input returned by the exchange. Do it again and it'll throw
     * a JSONException.
     */
    public static JSONObject convert(HttpExchange exchange) throws IOException, JSONException {
        // Create buffered input from exchange
        BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
        // Collect all lines and put them into one String
        String body = br.lines().collect(Collectors.joining(System.lineSeparator()));
        // Turn string into json object
        JSONObject json = new JSONObject(body);
        return json;
    }

    // Reference:
    // https://www.rgagnon.com/javadetails/java-get-url-parameters-using-jdk-http-server.html
    public static Map<String, String> convertFromGetToMap(HttpExchange exchange, String[] params) {
        String query = exchange.getRequestURI().getQuery();
        System.out.println(query);
        List<String> validParams = Arrays.asList(params);
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1 && validParams.contains(pair[0])) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }
  
    public static JSONObject convertFromGetToJSON(HttpExchange exchange, String[] params) {
        return new JSONObject(convertFromGetToMap(exchange, params));
    }
}