package utoronto.backend.controller.business.itinerary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItineraryImpl implements Itinerary {
    private String id;
    private String user_id;
    private int participants;
    double maxDistance;
    double maxBudget;
    int duration;
    private String country;
    private String city;
    private String date;
    private String startTime;
    private String endTime;
    private List<String> transportationModes;
    private List<String> chosenAttractions;
    private List<Event> events;

    public void construct() {
        // Because the builder pattern requires an empty constructor, this is where we
        // would have all the logic for actually creating the itinerary from the data
        // given by the user
    }

    @Override
    public String toString() {
        return "Country: " + country + "\nCity: " + city + "\ntransportation options selected: "
                + displayTransportationMethods() + "\ndate: " + date + "\nstartTime: " + startTime + "\nendTime: "
                + endTime + "\nparticipants: " + participants + "\nmaxBudget: " + maxBudget
                + "\nmaxDistance: " + maxDistance + "\nchosenAttractions: " + displayMultipleAttractions();

    }

    public String toJson() {

        JSONObject jsonContainer = new JSONObject();
        JSONObject event = new JSONObject();
        try {
            jsonContainer.put("id", getId());
            jsonContainer.put("user_id", getUser_id());
            jsonContainer.put("transportation", String.join(",", displayTransportationMethods())); //
            jsonContainer.put("participants", getparticipants());
            jsonContainer.put("maxDistance", getMaxDistance());
            jsonContainer.put("maxBudget", getMaxBudget());
            jsonContainer.put("country", getCountry());
            jsonContainer.put("city", getCity());
            jsonContainer.put("date", getDate());
            jsonContainer.put("startTime", getStartTime());
            jsonContainer.put("endTime", getEndTime());
            jsonContainer.put("duration", 60);
            jsonContainer.put("attractions", String.join(",", displayMultipleAttractions())); //
            if(this.events != null) jsonContainer.put("events", getEventsJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonContainer.toString();
    }

    private JSONArray getEventsJson() {
        JSONArray events = new JSONArray();
        for (Event event : this.events) {
            events.put(event.toJson());
        }
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setTransportationModes(List<String> transportationModes) {
        this.transportationModes = transportationModes;
    }

    public List<String> getTransportationModes() {
        return transportationModes;
    }

    public void setparticipants(int participants) {
        this.participants = participants;
    }

    public int getparticipants() {
        return this.participants;
    }

    /**
     * @param maxDistance the maxDistance to set
     */
    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    /**
     * @param maxBudget the maximum budget to set
     */
    public void setMaxBudget(double maxBudget) {
        this.maxBudget = maxBudget;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setChosenAttractions(List<String> attractions) {
        this.chosenAttractions = attractions;
    }

    private List<String> displayTransportationMethods() {
//        List<String> result = new ArrayList<String>();
//
//        for (String choice : transportationModes) {
//            switch (choice) {
//                case 1:
//                    choice = 1;
//                    result.add("walking");
//                    break;
//                case 2:
//                    choice = 2;
//                    result.add("driving");
//                    break;
//                case 3:
//                    choice = 3;
//                    result.add("taking public transit");
//                    break;
//                default:
//                    result.add("");
//            }
//        }
        return transportationModes;
    }

    private List<String> displayMultipleAttractions() {
//        List<String> result = new ArrayList<String>();
//
//        for (int choice : this.chosenAttractions) {
//            switch (choice) {
//                case 1:
//                    choice = 1;
//                    result.add("Beaches");
//                    break;
//                case 2:
//                    choice = 2;
//                    result.add("Malls");
//                    break;
//                case 3:
//                    choice = 3;
//                    result.add("Outlet Malls");
//                    break;
//                case 4:
//                    choice = 4;
//                    result.add("Landmarks");
//                    break;
//                default:
//                    result.add("");
//            }
//        }
        return chosenAttractions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getChosenAttractions() {
        return chosenAttractions;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public double getMaxBudget() {
        return maxBudget;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
