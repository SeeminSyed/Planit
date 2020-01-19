package utoronto.backend.controller.business.itinerarybuilder;

import java.util.List;
import java.util.Map;

import utoronto.backend.controller.business.itinerary.Event;
import utoronto.backend.controller.business.itinerary.Itinerary;

/**
 * Class responsible for creating Itineraries. Follows builder design pattern.
 */
public interface ItineraryBuilder {
    public Itinerary build();

//

	public boolean setparticipants(String participants);
    
    public boolean setMaxDistance(String maxDistance);

    public boolean setMaxBudget(String maxBudget);

    boolean setCountry(String country);
    
    boolean setCity(String city, String country);

    public boolean setDate(String date);

    public boolean setStartTime(String givenStartTime);

    public boolean setEndTime(String givenEndTime);

    public boolean setChosenAttractions(String attractions/*, int totalAttractions*/);
    
    public boolean setTransportationModes(String transportationModes);

    public boolean setEvents(List<Event> events);

    public boolean setUser_id(String user_id);

    public boolean setMaxBudget(double maxBudget);

    public boolean setMaxDistance(double maxDistance);

    public boolean setDuration(int duration);


}