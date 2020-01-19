package utoronto.backend.controller;

import org.json.JSONException;
import utoronto.backend.controller.business.itinerary.Itinerary;
import utoronto.backend.controller.business.itinerarybuilder.ItineraryBuilder;
import utoronto.backend.controller.business.itinerarybuilder.ItineraryBuilderImpl;
import utoronto.backend.controller.business.validation.ValidationImpl;
import utoronto.backend.controller.connection.Connector;
import utoronto.backend.model.service.DAOImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class ConsoleDemo {
    public static void run() {
        // call function for getting the distance => return distance
        // (prompting, validating, returning) => seperate functions

        ItineraryBuilder builder = new ItineraryBuilderImpl(new ValidationImpl());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DAOImpl dao = new DAOImpl();

        // Everyone elses code follows....
        setLocation(builder, br);
        setTransportationMethods(builder, br);
        setDateAndTime(builder, br);
        setparticipants(builder, br);
        setBudget(builder, br);
        setDistance(builder, br);
        setAttractions(builder, br, dao);

        Itinerary itinerary = builder.build();
        // For now, im just going to leave it at this because I dont know what yall
        // wanna do with this mf itinerary.

        System.out.println(itinerary);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, JSONException, InterruptedException {
        Connector.establishConnections();
        //run();
    }

    private static void setDistance(ItineraryBuilder builder, BufferedReader br) {
        String givenDistance = "";
        do {
            System.out.println("Please input the maximum distance you are willing to travel between events in kilometres.");
            try {
                givenDistance = br.readLine();
            } catch (IOException e) {
                givenDistance = "";
            }
            // I will honestly do error messages later i am running around and crying
            // having it while block forces the set
        } while (!builder.setMaxDistance(givenDistance));
    }


    private static void setLocation(ItineraryBuilder builder, BufferedReader br) {
        String country = "";
        String city = "";
        //Country
        do {
            System.out.println("Please input the country in which you are travelling");
            try {
                country = br.readLine();
            } catch (IOException e) {
                country = "";
            }
        } while (!builder.setCountry(country));

        //City
        do {
            System.out.println("Please input the city in which you are travelling");
            try {
                city = br.readLine();
            } catch (IOException e) {
                city = "";
            }
        } while (!builder.setCity(city, country));
    }

    private static void setDateAndTime(ItineraryBuilder builder, BufferedReader br) {
        String givenDate = "";
        String givenStartTime = "";
        String givenEndTime = "";
        // date
        do {
            System.out.println("Please input the day you want the itinerary for in the exact format of 'MM/DD/YYYY'");
            try {
                givenDate = br.readLine();
            } catch (IOException e) {
                givenDate = "";
                System.out.println("Please try again.");
                continue;
            }
        } while (!builder.setDate(givenDate));

        // times
        // start time
        do {
            System.out.println("Please input when your itinerary will start in the format HHMM (military time)");
            try {
                givenStartTime = br.readLine();
            } catch (IOException e) {
                givenStartTime = "";
                System.out.println("Please try again.");
                continue;
            }
        } while (!builder.setStartTime(givenStartTime));

        // end time
        do {
            System.out.println("Please input when your itinerary will end in the format HHMM (military time). This must be at least an hour after your the chosen start time.");
            try {
                givenEndTime = br.readLine();
            } catch (IOException e) {
                givenEndTime = "";
                System.out.println("Please try again.");
                continue;
            }
        } while (!builder.setEndTime(givenEndTime));
    }

    private static void setparticipants(ItineraryBuilder builder, BufferedReader br) {
        String participants = "";
        do {
            System.out.println("Please input the number of participants that the itinerary should be planned for.");
            try {
                participants = br.readLine();
            } catch (IOException e) {
                participants = "";
            }
        } while (!builder.setparticipants(participants));
    }

    private static void setBudget(ItineraryBuilder builder, BufferedReader br) {
        String givenBudget = "";
        do {
            System.out.println("Please provide a pricepoint that you would like to plan your trip around.");
            try {
                givenBudget = br.readLine();
            } catch (IOException e) {
                givenBudget = "";
            }
            // I will honestly do error messages later i am running around and crying
            // having it while block forces the set
        } while (!builder.setMaxBudget(givenBudget));
    }

    private static void setAttractions(ItineraryBuilder builder, BufferedReader br, DAOImpl dao) {
        String attractions = "";
        int totalAttractions = dao.getTotalAttractions();
        do {
            System.out.println("Please choose attractions from the list provided below:");
            System.out.println("Please format your input with commas in between. Example: '1, 2'");
            System.out.println(dao.getAttraction());
            try {
                attractions = br.readLine();
            } catch (IOException e) {
                attractions = "";
            }
        } while (!builder.setChosenAttractions(attractions/*, totalAttractions*/));
    }

    private static void setTransportationMethods(ItineraryBuilder builder, BufferedReader br) {
        String transportationModes = "";
        do {
            System.out.println("Please select a travel option(s). If you're choosing more than one travel option, please separate your options with a comma: "
                    + "\n1. Walking" + "\n2. Driving" + "\n3. Taking public transit");
            try {
                transportationModes = br.readLine();
            } catch (IOException e) {
                transportationModes = "";
            }
        } while (!builder.setTransportationModes(transportationModes));
    }
}
