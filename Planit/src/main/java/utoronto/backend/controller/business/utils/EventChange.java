package utoronto.backend.controller.business.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utoronto.backend.Globals;

public class EventChange {

    public CloseableHttpClient client;

    public EventChange() {
        this.client = Globals.client.getConnectorObject();
    }

    public String detailsEndPoint(String venue_id) throws JSONException, IOException {
        String uri = Globals.detailsAPI;
        HttpGet request = 
            new HttpGet(uri + venue_id
            + "?client_id=" + Globals.clientId
            + "&client_secret=" + Globals.clientSecret
            + "&v=" + Globals.version
            );
        request.addHeader("Content-Type", "application/json");
        System.out.print(request.toString());
        CloseableHttpResponse eventResponse = client.execute(request);
        // Get HttpResponse Status
        HttpEntity entity = eventResponse.getEntity();
        if (entity == null)
            return null;

        JSONObject data = new JSONObject(EntityUtils.toString(entity));
        eventResponse.close();
        int res_code = data.getJSONObject("meta").getInt("code");
        if (res_code != 200) {
            return null;
        }
        // retrieve data from 4sq api
        JSONObject res = data.getJSONObject("response").getJSONObject("venue");
        String eventID = res.getString("id");
        String name = res.getString("name");
        String contact = res.getJSONObject("contact").has("formattedPhone") ? 
            res.getJSONObject("contact").getString("formattedPhone") : "";
        String location = res.getJSONObject("location").getString("address");
        double lat = res.getJSONObject("location").getDouble("lat");
        double lng = res.getJSONObject("location").getDouble("lng");
        int price = res.has("price") ? res.getJSONObject("price").getInt("tier") : -1;
        double rating = res.has("rating") ? res.getDouble("rating") : 0;
        String details = res.has("details") ? res.getString("description"): "";
        // construct and return the response
        String response = "{ \"eventId\": " + "\"" + eventID + "\"," 
                            + "\"name\":" + "\"" + name + "\","
                            + "\"address\":" + "\"" + location + "\","
                            + "\"averageRating\":" + rating/2 + ","
                            + "\"details\":" + "\"" + details + "\","
                            + "\"contact\":" + "\"" + contact + "\","
                            + "\"cost\":" + price*10 + ","
                            + "\"lat\":" + lat + ","
                            + "\"lng\":" + lng
                            +  "}";
        return response;
    }
}
