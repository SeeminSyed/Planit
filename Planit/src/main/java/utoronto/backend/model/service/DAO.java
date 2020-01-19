package utoronto.backend.model.service;

import java.util.Map;

import utoronto.backend.controller.business.itinerary.Itinerary;

/**
 * The MockObject for a DAO. At this point, some of the data we use is from
 * this dao. The idea for this next sprint is to remove things from
 * here and have the generated via code/ taken from the database.
 */
public interface DAO {

    /**
     * @return A comma seperated list of transportation methods in string format.
     */
    public String getTransportationMethods();

    /**
     * @return A comma seperated list of attractions in string format.
     */
    public String getAttraction();

    /**
     * @return The total number of attractions returned when getAttraction() is called.
     */
    public int getTotalAttractions();

    /**
     * @return The valid countries and cities that can be inputted.
     */
    public Map<String, String> getCountriesandCities();

    /**
     * @return An Itinerary object. Because of how we coded it in console demo,
     *         this object may not have all of its information filled out.
     */
    public Itinerary getItinerary();
 }