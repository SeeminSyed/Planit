package utoronto.backend.controller.business.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import utoronto.backend.model.service.DAOImpl;

public class ValidationImpl implements Validation {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
    private DAOImpl dao = new DAOImpl();

    public boolean validateAttractions(String attractions, int totalAttractions) {
        // 4 cases

        // if (attractions.length() > 1 && !attractions.contains(",")) {
        //     System.err.println("Please seperate your choices by a comma");
        //     return false;
        // }
        // 1- len of str > 1 and don't seperate them with commas
        // otherwise split it
        String[] choices = attractions.split(",");
        // 2- more choices entered than given
        if (choices.length < 1) {
            System.err.println("Invalid entry");
            return false;
        }
        if (choices.length > totalAttractions) {
            System.err.println("Please enter numbers within the range of attractions given.");
            return false;
        }
        // go through and see if each number is an int
        for (String choice : choices) {
            try {
                // 3- check if choice is an int
                if (choice.equals("")) {
                    System.err.println("Invalid entry");
                    return false;
                }
                int num = Integer.parseInt(choice.trim());
                // 4- check if num is within total attractions
                if (num > totalAttractions) {
                    System.err.println("Please enter numbers within the range of attractions given.");
                    return false;
                }
            } catch (Exception e) {
                System.err.println("Please enter numbers within the range of attractions given.");
                return false;
            }
        }
        return true;
    }

    public boolean validateDistance(String givenDistance) {
        try {
            float distance = Float.parseFloat(givenDistance);
            if (distance < 1) {
                // distance must be greater than 0
                System.out.println("Please enter a value greater than or equal to 1.");
                return false;
            }
            if (distance > 50) {
                // For now, distance must be less than 50
                System.out.println("Please enter a value less than or equal to 50.");
                return false;
            }
        } catch (NullPointerException e) {
            // return false if string is null
            System.out.println("Please enter a numerical value.");
            return false;

        } catch (NumberFormatException e) {
            //  return false if string isnt a number
            System.out.println("Please enter a numerical value.");
            return false;
        }
        return true;
    }

    // implement your validation function here
    public boolean validateBudget(String givenBudget) {
        try {
            float budget = Float.parseFloat(givenBudget);
            if (budget < 1) {
                // budget must be greater than 0
                System.out.println("The minimum budget you can have is 1. Please re-enter.");
                return false;
            }

        } catch (NullPointerException e) {
            // return false if string is null
            System.out.println("Please enter a numerical value.");
            return false;
        } catch (NumberFormatException e) {
            //  return false if string isnt a number
            System.out.println("Please enter a numerical value.");
            return false;
        }
        return true;
    }

    public boolean validateCountry(String country) {

        try {
            //Check if the country inputted exists
            Map<String, String> CountriesandCitiesMap = dao.getCountriesandCities();
            country = country.trim().toLowerCase();
            if (!(CountriesandCitiesMap.containsValue(country))) {
                System.out.println("This is not a valid country");
                return false;
            }
        } catch (NullPointerException e) {
            // return false if string is null
            return false;
        }
        return true;
    }

    public boolean validateDate(String dateString) {
        try {
            dateFormat.setLenient(false);
            // get current date
            Date date = dateFormat.parse(dateString);

            Date currentDate = dateFormat.parse(dateFormat.format(new Date()));
            // get user's date. this uses the provided pattern to parse string into date object
            if (date.compareTo(currentDate) < 0) {
                // if the date is in the past, return false
                System.out.println("Must be current or future date.");
                return false;
            }
        } catch (NullPointerException e1) {
            System.out.println("Please try again.");
            return false;
        } catch (ParseException e2) {
            System.out.println("Invalid format.");
            return false;
        }
        return true;
    }

    public boolean validateStartTime(String startTimeString, String dateString) {
        try {
            timeFormat.setLenient(false);
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateString);
            // try to convert to long
            long startTimeLong = Long.parseLong(startTimeString);

            // check between 0000 and (2359 - 100 = 2259)
            if (startTimeLong < 0000 || startTimeLong > 2259) {
                System.out.println("Start time should be between 0000 and 2259.");
                return false;
            }

            Date startTime = timeFormat.parse(startTimeString);

            // check if current day then > current time
            Date currentDate = dateFormat.parse(dateFormat.format(new Date()));
            Date currentTime = timeFormat.parse(timeFormat.format(new Date()));
            if (date.compareTo(currentDate) == 0 && startTime.getTime() <= (currentTime.getTime())) {
                System.out.println("Start time should be after current time as the itinerary is for today.");
                return false;
            }

        } catch (NumberFormatException e1) {
            System.out.println("Invalid format.");
            return false;
        } catch (ParseException e2) {
            System.out.println("Invalid format.");
            return false;
        }
        return true;
    }

    public boolean validateCity(String city, String country) {

        try {
            //Check if the city inputed exists
            Map<String, String> CountriesandCitiesMap = dao.getCountriesandCities();
            country = country.trim().toLowerCase();
            city = city.trim().toLowerCase();
            if (!(CountriesandCitiesMap.containsKey(city))) {
                System.out.println("This is not a valid city");
                return false;
            }

            //Check if the city exists in the country
            String actualCountry = CountriesandCitiesMap.get(city);
            if (!actualCountry.equals(country)) {
                System.out.println(city + " is not in the country " + country);
                return false;
            }
        } catch (NullPointerException e) {
            // return false if string is null
            return false;
        }
        return true;
    }

    public boolean validateEndTime(String endTimeString, String startTimeString) {
        try {
            dateFormat.setLenient(false);
            timeFormat.setLenient(false);

            // These two lines arent setting anything, but this parse forces  the usr to have correct formatting,
            // otherwise it throws an parse exception.
            timeFormat.parse(startTimeString);
            timeFormat.parse(endTimeString);

            // check convert to long
            long endTimeLong = Long.parseLong(endTimeString);
            long startTimeLong = Long.parseLong(startTimeString);
            // check between start and 2359
            if (endTimeLong < startTimeLong + 100 || endTimeLong > 2359) {
                System.out.println("End time should be between start time and 2359, and at least an hour long.");
                return false;
            }
        } catch (NumberFormatException e1) {
            System.out.println("Invalid format.");
            return false;
        } catch (ParseException e2) {
            System.out.println("Invalid format.");
            return false;
        }
        return true;
    }

    public SimpleDateFormat getDateFormat() {
        return this.dateFormat;
    }

    public SimpleDateFormat getTimeFormat() {
        return this.timeFormat;
    }

    public boolean validateparticipants(String participants) {
        try {
        	String num = participants.trim();
            int numParticipants = Integer.parseInt(num);
            if (numParticipants < 1) {
                // number of participants must be greater than 0
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean validateTransportationModes(String transportationModes) {

        String[] choices = transportationModes.split(",");
        for (String choice : choices) {
            try {
                int num = Integer.parseInt(choice.trim());
                if (num > 3 || num < 1) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
