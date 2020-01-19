package utoronto.backend.controller.business.itinerary;

import java.util.ArrayList;
import java.util.List;

public interface Itinerary {
    public void construct();

    // itinerary methods here

    @Override
    public String toString();

    public String toJson();

    public void setEvents(List<Event> events);

    public List<Event> getEvents();

    public void setTransportationModes(List<String> transportationModes);

    public List<String> getTransportationModes();

    public void setparticipants(int participants);

    public int getparticipants();

    public void setMaxDistance(double maxDistance);

    public void setMaxBudget(double maxBudget);

    public void setCountry(String country);

    public String getCountry();

    public void setCity(String city);

    public String getCity();

    public void setDate(String date);

    public String getDate();

    public void setStartTime(String startTime);

    public String getStartTime();

    public void setEndTime(String endTime);

    public void setChosenAttractions(List<String> attractions);

    public String getId();

    public void setId(String id);

    public List<String> getChosenAttractions();

    public double getMaxDistance();

    public double getMaxBudget();

    public String getEndTime();

    public String getUser_id();

    public void setUser_id(String user_id);

    public int getDuration();

    public void setDuration(int duration);
}

