package utoronto.backend.model.service;

import utoronto.backend.controller.business.itinerary.Event;
import utoronto.backend.controller.business.itinerary.Itinerary;
import utoronto.backend.controller.business.itinerarybuilder.ItineraryBuilder;
import utoronto.backend.controller.business.itinerarybuilder.ItineraryBuilderImpl;
import utoronto.backend.controller.business.utils.EventCreateHelper;
import utoronto.backend.controller.business.validation.ValidationImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see utoronto.backend.model.service.DAO
 */
public class DAOImpl implements DAO {

    private EventCreateHelper eventCreate;

    /**
     * @see {@link DAO#getTransportationMethods()}
     */
    public String getTransportationMethods() {
        return "Walking,Driving,Taking public transit";
    }

    /**
     * @see {@link DAO#getAttraction()}
     */
    public String getAttraction() {
        return "Beaches,Malls,Outlet Malls,Landmarks";
    }

    /**
     * @see {@link DAO#getTotalAttractions()}
     */
    public int getTotalAttractions() {
        return 4;
    }

    /**
     * @see {@link DAO#getCountriesandCities()}
     */
    public Map<String, String> getCountriesandCities() {

        // Add a few Countries and Cities for testing

        Map<String, String> CountriesandCities = new HashMap<String, String>();
        CountriesandCities.put("toronto", "canada");
        CountriesandCities.put("chicago", "usa");

        return CountriesandCities;

    }

    /**
     * Note that this itinerary given does NOT have all the information inputted.
     * It only has the ones where getters are implemented in Itinerary.
     * @see {@link utoronto.backend.controller.business.itinerary.Itinerary}
     * @see {@link DAO#getItinerary()}
     */
    @Override
    public Itinerary getItinerary() {
        ItineraryBuilder builder = new ItineraryBuilderImpl(new ValidationImpl());
        builder.setDate("12/12/2020");
        builder.setStartTime("1400");
        builder.setparticipants("3");
        builder.setEndTime("1800");
        builder.setCity("Toronto", "Canada");
        builder.setCountry("Canada");
        builder.setMaxBudget("500.00");
        List<Event> events = eventCreate.getItineraryEvents("Toronto", "Canada", 500.00, "0000", "1000");
        builder.setEvents(events);
        builder.setChosenAttractions("Steakhouses");
        return builder.build();
    }
}