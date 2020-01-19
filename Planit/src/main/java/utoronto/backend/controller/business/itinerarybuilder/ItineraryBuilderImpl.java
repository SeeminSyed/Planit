package utoronto.backend.controller.business.itinerarybuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utoronto.backend.controller.business.itinerary.Event;
import utoronto.backend.controller.business.itinerary.Itinerary;
import utoronto.backend.controller.business.itinerary.ItineraryImpl;
import utoronto.backend.controller.business.validation.Validation;

public class ItineraryBuilderImpl implements ItineraryBuilder {

    private Validation validation;

    // If youre reading this code, you might be confused as to why I'm putting the
    // itinerary class
    // here as opposed to it's parameters. There are several reasons for this.
    // This decouples the Itinerary from the builder (having the same attributes
    // tight couples the classes) and also removes duplicate code.
    // If you have any problems with me doing this, feel free to change it back.
    // Regardless of how you implement it, the result is the same.
    private Itinerary itinerary;

    public ItineraryBuilderImpl(Validation validation) {
        this.validation = validation;
        this.itinerary = new ItineraryImpl();
    }

    public Itinerary build() {

        itinerary.construct();
        // return itinerary here
        return itinerary;
    }

    public boolean setMaxDistance(String maxDistance) {

//        if (validation.validateDistance(maxDistance)) {
            itinerary.setMaxDistance(Float.parseFloat(maxDistance));
            return true;
//        }
//        return false;
    }

    public boolean setMaxBudget(String maxBudget) {
//        if (validation.validateBudget(maxBudget)) {
            itinerary.setMaxBudget(Float.parseFloat(maxBudget));
            return true;
//        }
//        return false;
    }

    public boolean setCountry(String country) {
//        if (validation.validateCountry(country)) {
            itinerary.setCountry(country);
            return true;
//        }
//        return false;
    }

    public boolean setCity(String city, String country) {
//        if (validation.validateCity(city, country)) {
            itinerary.setCity(city);
            return true;
//        }
//        return false;
    }

    public boolean setDate(String date) {
//        if (validation.validateDate(date)) {
            itinerary.setDate(date);
            return true;
//        }
//        // if invalid or not added
//        System.out.println("Please try again.");
//        return false;
    }

    public boolean setStartTime(String givenStartTime) {
//        if (validation.validateStartTime(givenStartTime, itinerary.getDate())) {
            itinerary.setStartTime(givenStartTime);
            return true;
//        }
//        // if invalid or not added
//        System.out.println("Please try again.");
//        return false;
    }

    public boolean setEndTime(String givenEndTime) {
//        if (validation.validateEndTime(givenEndTime, itinerary.getStartTime())) {
            itinerary.setEndTime(givenEndTime);
            return true;
//        }
//        // if invalid or not added
//        System.out.println("Please try again.");
//        return false;
    }

    public boolean setChosenAttractions(String attractions/*, int totalAttractions*/) {
//        if (validation.validateAttractions(attractions, totalAttractions)) {
//            ArrayList<Integer> chosenAttractions = this.stringToArrayList(attractions);
            List<String> chosenAttractions = new ArrayList<>();
            chosenAttractions.add(attractions);
            itinerary.setChosenAttractions(chosenAttractions);
            return true;
//        }
//        return false;
    }

    public boolean setparticipants(String participants) {
        String numParticipants = participants.trim();
        if (validation.validateparticipants(numParticipants)) {
            itinerary.setparticipants(Integer.parseInt(numParticipants));
            return true;
        }

        // if invalid or not added
        System.out.println("Invalid input. Please try again.");
        return false;
    }

    private ArrayList<Integer> stringToArrayList(String input) {
        // example of input: "1, 2, 3"

        String[] options = input.split(",");
        ArrayList<Integer> chosenOptions = new ArrayList<Integer>();
        // loop through the array > convert each to an int > add it to the arraylist
        for (String option : options) {
            int num = 0;
            try {
                num = Integer.parseInt(option.trim());
            } catch (NumberFormatException e) {
                return null;
            }
            if (num != 0 && !chosenOptions.contains(num)) {
                chosenOptions.add(num);
            }
        }
        return chosenOptions;
    }

    public boolean setTransportationModes(String transportationModes) {

//        if (validation.validateTransportationModes(transportationModes)) {
//            ArrayList<Integer> chosenOptions = stringToArrayList(transportationModes);
            List<String> chosenOptions = new ArrayList<>();
            chosenOptions.add(transportationModes);
            chosenOptions.add(transportationModes);
            itinerary.setTransportationModes(chosenOptions);
            return true;
//        }

//        System.out.println("Invalid input. Please try again.");
//        return false;
    }

    public boolean setEvents(List<Event> events) {
        itinerary.setEvents(events);
        return true;
    }


    // TODO: validation T-T
    @Override
    public boolean setUser_id(String user_id) {
//        if (validation.validateUser_id(user_id, itinerary.getUser_id())) {
            itinerary.setUser_id(user_id);
            return true;
//        }
//        // if invalid or not added
//        System.out.println("Please try again.");
//        return false;

    }

    @Override
    public boolean setMaxBudget(double maxBudget) {
//        if (validation.validateMaxBudget(maxBudget, itinerary.getMaxBudget())) {
            itinerary.setMaxBudget(maxBudget);
            return true;
//        }
//        // if invalid or not added
//        System.out.println("Please try again.");
//        return false;
    }

    @Override
    public boolean setMaxDistance(double maxDistance) {
//        if (validation.validateMaxDistance(maxDistance, itinerary.getMaxDistance())) {
            itinerary.setMaxDistance(maxDistance);
            return true;
//        }
//        // if invalid or not added
//        System.out.println("Please try again.");
//        return false;

    }

    @Override
    public boolean setDuration(int duration) {
//        if (validation.validateDuration(duration, itinerary.getDuration())) {
            itinerary.setDuration(duration);
            return true;
//        }
//        // if invalid or not added
//        System.out.println("Please try again.");
//        return false;

    }

}
