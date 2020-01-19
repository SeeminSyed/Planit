package utoronto.backend.controller.business.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utoronto.backend.Globals;
import utoronto.backend.controller.business.itinerary.Event;
import utoronto.backend.controller.business.itinerary.EventImpl;
import utoronto.backend.model.service.insertdao.InsertDAO;
import utoronto.backend.model.service.retrievedao.RetrieveDAO;
import utoronto.backend.model.service.updatedao.UpdateDAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class EventCreateHelper {

    public CloseableHttpClient client;

    public EventCreateHelper() {
        this.client = Globals.client.getConnectorObject();
    }

    // get Map of int and event objects
    public List<Event> getItineraryEvents(String city, String country, double maxBudget, String startTimeString, String endTimeString) {
        try {

            // get number of results based on time
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
            timeFormat.setLenient(false);
            Date startTime = timeFormat.parse(startTimeString);
            Date endTime = timeFormat.parse(endTimeString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            int startHour = cal.get(Calendar.HOUR_OF_DAY);
            int startMinutes = cal.get(Calendar.MINUTE);
            cal.setTime(endTime);
            int endHour = cal.get(Calendar.HOUR_OF_DAY);
            int endMinutes = cal.get(Calendar.MINUTE);
            int limit = ((((endHour - startHour) * 60) + (endMinutes - startMinutes)) / 60);

            // get price tier
            double tier = (maxBudget / limit);
            String priceTiers = "1";
            if (10.0 < tier) priceTiers += ",2";
            if (20.0 < tier) priceTiers += ",3";
            if (30.0 < tier) priceTiers += ",4";

            String uri = "https://api.foursquare.com/v2/venues/explore";
            HttpGet request = new HttpGet(uri + "?"
                    + "client_id=ZCTFI0ZES0C0AIGDJQJYY2RAZON2DZWZ5CO13HR2VCYYBH20"
                    + "&client_secret=N5RD3ONTA32ZLUAQFHUE5Y0NAWV05FBJXSS1WDX1XBLKEJG1"
                    + "&v=20191212"
                    + "&near=" + city.replace(" ", "%20") + ",%20" + country.replace(" ", "%20")
                    + "&section=" + "nextVenues"
                    + "&limit=" + limit
                    + "&offset=" + "5"
                    + "&time=any"
                    + "&day=any"
                    + "&price=" + priceTiers
            );
//            System.out.println(request.toString());
            request.addHeader("Content-Type", "application/json");

            // parse response
            CloseableHttpResponse eventResponse = client.execute(request);
            // Get HttpResponse Status
            HttpEntity entity = eventResponse.getEntity();
            if (entity == null) return null;

            JSONObject data = new JSONObject(EntityUtils.toString(entity));
            eventResponse.close();

            JSONObject response = data.getJSONObject("response");
            JSONArray items = response.getJSONArray("groups").getJSONObject(0).getJSONArray("items");

            // get specific event details
            List<Event> events = new ArrayList<>();
            String id;
            Event temp;
            cal.setTime(startTime);
            for (int i = 0; i < items.length(); ++i) {
//                id = items.getJSONObject(i).getJSONObject("venue").getString("id");
//                temp = getEventDetails(id);
                temp = getBasicEventDetails(items.getJSONObject(i).getJSONObject("venue"));
                if (temp != null) {
                    temp.setStart_time(timeFormat.format(cal.getTime()));
                    cal.add(Calendar.HOUR_OF_DAY, 1);
                    temp.setEnd_time(timeFormat.format(cal.getTime()));
                    events.add(temp);
                }
            }

            return events;

            // for testing
//            EventCreateHelper a = new EventCreateHelper();
//            Map<Integer, Event> events = a.getItineraryEvents("marina bay", "singapore", 500.00);
//            for (int i: events.keySet()) {
//                System.out.println(events.get(i).toString());
//            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Event getBasicEventDetails(JSONObject venue) {
        try {
            String event_id = venue.getString("id");
            Event event = new EventImpl(event_id);
            event.setName(venue.optString("name"));
            event.setAddress(venue.optJSONObject("location").optString("address"));
            event.setLat(venue.optJSONObject("location").optDouble("lat"));
            event.setLng(venue.optJSONObject("location").optDouble("lng"));
            event.setCategory(venue.optJSONArray("categories").optJSONObject(0).optString("name"));
            event.setDetails(venue.optString("description"));
            if (venue.optJSONObject("price") != null) {
                event.setCost(venue.optJSONObject("price").optInt("tier", 0) * 10.0);
            }
            if (venue.optJSONObject("contact") != null) {
                event.setContact(venue.optJSONObject("contact").optString("formattedPhone"));
            }
            event.setAverageRating(venue.optDouble("rating", 0));

            return event;

        } catch (JSONException e) {
//            e.printStackTrace();
            return null;
        }
    }

    // given event id, returns an event object
    private Event getEventDetails(String event_id) {
        try {
            String uri = "https://api.foursquare.com/v2/venues/";
            HttpGet request = new HttpGet(uri
                    + event_id
                    + "?"
                    + "client_id=ZCTFI0ZES0C0AIGDJQJYY2RAZON2DZWZ5CO13HR2VCYYBH20"
                    + "&client_secret=N5RD3ONTA32ZLUAQFHUE5Y0NAWV05FBJXSS1WDX1XBLKEJG1"
                    + "&v=20191212"
            );
//            System.out.println(request.toString());
            request.addHeader("Content-Type", "application/json");

            CloseableHttpResponse eventResponse = client.execute(request);
            // Get HttpResponse Status
            HttpEntity entity = eventResponse.getEntity();
            System.out.println("1 lore");
            if (entity == null) return null;
            System.out.println("2 loli");

            JSONObject data = new JSONObject(EntityUtils.toString(entity));
            eventResponse.close();

            

            JSONObject venue = data.getJSONObject("response").getJSONObject("venue");
            Event event = new EventImpl(event_id);
            event.setName(venue.optString("name"));
            event.setAddress(venue.optJSONObject("location").optString("address"));
            event.setLat(venue.optJSONObject("location").optDouble("lat"));
            event.setLng(venue.optJSONObject("location").optDouble("lng"));
            event.setCategory(venue.optJSONArray("categories").optJSONObject(0).optString("name"));
            event.setDetails(venue.optString("description"));
            if (venue.optJSONObject("price") != null) {
                event.setCost(venue.optJSONObject("price").optInt("tier", 0) * 10.0);
            }
            if (venue.optJSONObject("contact") != null) {
                event.setContact(venue.optJSONObject("contact").optString("formattedPhone"));
            }
            event.setAverageRating(venue.optDouble("rating", 0));

            return event;

        } catch (JSONException | IOException e) {
//            e.printStackTrace();
            return null;
        }
    }

}
