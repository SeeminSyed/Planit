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

public class EventSearch {

    public CloseableHttpClient client;

    public EventSearch() {
        this.client = Globals.client.getConnectorObject();
    }

    public String exploreEndPoint(String near, String query, String section, String price)
            throws JSONException, IOException {
        String uri = Globals.exploreAPI;
        String venues = "";
        int limit = 10;
        HttpGet request = 
            new HttpGet(uri
                + "?client_id=" + Globals.clientId
                + "&client_secret=" + Globals.clientSecret
                + "&v=" + Globals.version
                + "&near=" + near.replace(" ", "%20")
                + "&section=" + section.replace(" ", "%20")
                + "&query=" + query.replace(" ", "%20")
                + "&limit=" + limit
                + "&offset=" + "5"
                + "&price=" + price
            );
        request.addHeader("Content-Type", "application/json");

        CloseableHttpResponse eventResponse = client.execute(request);
        // Get HttpResponse Status
        HttpEntity entity = eventResponse.getEntity();
        if (entity == null)
            return null;

        JSONObject data = new JSONObject(EntityUtils.toString(entity));
        eventResponse.close();

        // check if response code is successful => return null
        int res_code = data.getJSONObject("meta").getInt("code");
        if (res_code != 200) {
            return null;
        }

        JSONArray response = data.getJSONObject("response").getJSONArray("groups").getJSONObject(0).getJSONArray("items");
        for (int i = 0; i < Math.min(limit, response.length()); i++){
            String id = response.getJSONObject(i).getJSONObject("venue").getString("id");
            String name = response.getJSONObject(i).getJSONObject("venue").getString("name");
            String address = response.getJSONObject(i).getJSONObject("venue").getJSONObject("location").getString("address");
            String venue = "{ \"id\": " + "\"" + id + "\"" + ", \"name\": " + "\"" + name + "\"" + ", \"address\": " + "\"" + address + "\"" +"},";
            venues = venues + venue;
        }

        // return null;
        return "{ \"venues\": [" + venues.substring(0, venues.length() - 1) + "] }";
    }
}