// dart doesnt have interfaces but according to stackoverflow, abstract classes are just as good
import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/itinerary/itneraryDetails.object.dart';

abstract class Itinerary {

    List<String> getTransportationModes();

    int getNumberOfParticipants();

    int getMaxDistance();

    int getMaxBudget();

    String getCountry();

    String getCity();

    String getDate();

    String getStartTime();

    String getEndTime();

    List<String> getChosenAttractions();

    List<String> getTimings();
    
    List<Event>  getEvents();

    ItineraryDetails getDetails();

    void replaceEvent(Event oldEvent, Event newEvent);
}