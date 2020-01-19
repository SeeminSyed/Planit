package utoronto.backend.controller.business.validation;

/**
 * Validates user input given for creating itineraries.
 */
public interface Validation {
    // put your validation functions here
    public boolean validateAttractions(String attractions, int totalAttractions);

    public boolean validateparticipants(String participants);

    public boolean validateDistance(String givenDistance);

    public boolean validateBudget(String givenBudget);
    
    boolean validateCountry(String country);

    boolean validateCity(String city, String country);

    boolean validateDate(String date);

    boolean validateStartTime(String startTimeString, String dateString);

    boolean validateEndTime(String endTimeString, String startTimeString);
    
    public boolean validateTransportationModes(String transportationModes);
}