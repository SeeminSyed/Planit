import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';
import 'package:planit_frontend/objects/itinerary/itneraryDetails.object.dart';

abstract class ItineraryBuilder {

  Itinerary build();

  Itinerary buildEmpty(ItineraryDetails details);

  ItineraryBuilder setTransportationModes(List<String> transportationModes);

  ItineraryBuilder setNumberOfParticipants(int numberOfParticipants);

  ItineraryBuilder setMaxDistance(int maxDistance);

  ItineraryBuilder setMaxBudget(int maxBudget);

  ItineraryBuilder setCountry(String country);

  ItineraryBuilder setCity(String city);

  ItineraryBuilder setDate(String date);

  ItineraryBuilder setStartTime(String startTime);

  ItineraryBuilder setEndTime(String parseLong);

  ItineraryBuilder setChosenAttractions(List<String> attractions);

  ItineraryBuilder setEvents(List<Event> events);

  ItineraryBuilder setTimings(List<String> timings);

}