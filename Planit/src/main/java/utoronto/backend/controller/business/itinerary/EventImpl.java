package utoronto.backend.controller.business.itinerary;

import org.json.JSONException;
import org.json.JSONObject;

public class EventImpl implements Event {
    private String event_id;
    private String name = "";
    private String start_time = "";
    private String end_time = "";
    private double cost = 0.0;
    private String address = "";
    private Double lat = 0.0;
    private Double lng = 0.0;
    private String category = "";
    private String details = "";
    private String contact = "";
    private Double averageRating = 0.0;

    public EventImpl(String event_id) {
        this.event_id = event_id;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonContainer = new JSONObject();
        JSONObject event = new JSONObject();

        try {
            jsonContainer.put("eventStartTime", getStart_time());
            jsonContainer.put("eventEndTime", getEnd_time());
            jsonContainer.put("totalTime", 60);

            event.put("eventId", getEvent_id());
            event.put("name", getName());
            event.put("address", getAddress());
            event.put("averageRating", getAverageRating());
            event.put("details", getDetails());
            event.put("contact", getContact());
            event.put("cost", getCost());
            event.put("category", getCategory());

            jsonContainer.put("event", event);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonContainer;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }


    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    public double getCost() {
        return cost;
    }
}
