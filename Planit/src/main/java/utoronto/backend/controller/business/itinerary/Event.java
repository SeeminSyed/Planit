package utoronto.backend.controller.business.itinerary;

import org.json.JSONObject;

public interface Event {
    public JSONObject toJson();

    public String getEvent_id();

    public void setEvent_id(String event_id);

    public String getStart_time();

    public void setStart_time(String start_time);

    public String getEnd_time();

    public void setEnd_time(String end_time);

    public String getName();

    public void setName(String name);

    public Double getLat();

    public void setLat(Double lat);

    public Double getLng();

    public void setLng(Double lng);

    public String getCategory();

    public void setCategory(String category);

    public String getAddress();

    public void setAddress(String address);

    public Double getAverageRating();

    public void setAverageRating(Double averageRating);

    public void setCost(double cost);

    public String getDetails();

    public void setDetails(String details);

    public String getContact();

    public void setContact(String contact);

    public double getCost();
}
