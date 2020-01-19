package utoronto.backend.controller.business.validation;

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

/**
 * The class responsible for checking if a location is actually valid or not.
 * This class uses a public api to do so.
 */
public class LocationValidationImpl extends ValidationImpl {

    public CloseableHttpClient client;

    public LocationValidationImpl() {
        this.client = Globals.client.getConnectorObject();
    }
    
    /**
     * see {@link Validation#validateCountry(String)}
     */
    @Override
    public boolean validateCountry(String country) {
        if (country == null) return false;
        String countryId = getCountryIds(country);
        return countryId != null && countryId.length() > 0; 
    }

    /**
     * see {@link Validation#validateCity(String, String)}
     */
    @Override
    public boolean validateCity(String city, String country) {
        if (country == null || city == null) return false;
        String countryIds = getCountryIds(country);
        if (countryIds == null || countryIds.length() <= 0) return false;
        Boolean validCity = checkCity(city, countryIds);
        return validCity;
    }

    /**
     * @param country The exact name of the country to check
     *     (ie, "United States" or "USA" will fail. "United States of America"
     *     is required).
     * @return The country id of the given country.
     */
    private String getCountryIds(String country) {

    
    // Code logic taken from
    // https://openjdk.java.net/groups/net/httpclient/intro.html

        String exactCountryId = null;

        try {
            // This sleep is here because you can only send requests to the API twice per
            // second
            // with the free version. This is at the start so in case this method gets
            // called right
            // after a metYYhod that uses this api, it wont fail.
            Thread.sleep(500);
            String uri = "http://geodb-free-service.wirefreethought.com/v1/geo/countries";

            HttpGet request = new HttpGet(uri + "?namePrefix=" + country.trim().replace(" ", "%20"));
            request.addHeader("Content-Type", "application/json");
            
            CloseableHttpResponse countryResponse = client.execute(request);
            // Get HttpResponse Status

            HttpEntity entity = countryResponse.getEntity();
            if (entity == null) return null;

            JSONObject parsed = new JSONObject(EntityUtils.toString(entity));
            countryResponse.close();
            
            JSONArray countries = parsed.getJSONArray("data");
            // Implies country doesnt exist, exit function
            if (countries.length() <= 0)
                return null;

            // Everything after this point assumes country given is valid
            country = country.toLowerCase();
            for (int i = 0; i < countries.length() || exactCountryId.equals(null); i++) {
                JSONObject countryData = countries.getJSONObject(i);
                String countryName = countryData.getString("name");

                if (countryName.toLowerCase().equals(country)) {
                    exactCountryId = countryData.getString("wikiDataId");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return exactCountryId;
    }

    private boolean checkCity(String city, String countryId) {
        
        try {
            // This sleep is here because you can only send requests to the API twice per
            // second
            // with the free version. This is at the start so in case this method gets
            // called right
            // after a method that uses this api, it wont fail.
            Thread.sleep(500);

            String uri = "http://geodb-free-service.wirefreethought.com/v1/geo/cities";

            HttpGet request = new HttpGet(uri + "?"
                + "namePrefix=" + city.replace(" ", "%20")
                + "&minPopulation=1000000"
                + "&limit=10" 
                + "&countryIds=" + countryId);
            request.addHeader("Content-Type", "application/json");
            
            CloseableHttpResponse cityResponse = client.execute(request);
            // Get HttpResponse Status

            HttpEntity entity = cityResponse.getEntity();
            if (entity == null) return false;

            JSONObject data = new JSONObject(EntityUtils.toString(entity));
            cityResponse.close();
            JSONArray cities = data.getJSONArray("data");

            // Everything after this point assumes country given is valid

            city = city.toLowerCase();
            for (int i = 0; i < cities.length(); i++) {
                JSONObject cityData = cities.getJSONObject(i);
                String cityName = cityData.getString("name");
                if (cityName.toLowerCase().equals(city)) {
                    return true;
                }
            }

            return false;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;

    }
}