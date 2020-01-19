import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/itinerary/itineraryBuilder.object.dart';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';
import 'package:planit_frontend/objects/itinerary/itneraryDetails.object.dart';
import 'itineraryImpl.object.dart';

class ItineraryBuilderImpl implements ItineraryBuilder {
  
  ItineraryImpl itinerary;

  Itinerary buildEmpty(ItineraryDetails details) {
    ItineraryImpl blank = new ItineraryImpl();
    blank.details = details;
    blank.events = new List<Event>();
    blank.timings = new List<String>();
    return blank;
  }

  ItineraryBuilderImpl() {
    this.itinerary = new ItineraryImpl();
  }

  Itinerary build() {
    return itinerary;
  }

	ItineraryBuilder setNumberOfParticipants(int numberOfParticipants) {
    itinerary.details.numberOfParticipants = numberOfParticipants;
    return this;
  }
    
  ItineraryBuilder setMaxDistance(int maxDistance){
    itinerary..details.maxDistance = maxDistance;
    return this;
  }

  ItineraryBuilder setMaxBudget(int maxBudget) {
    itinerary.details.maxBudget = maxBudget;
    return this;
  }

  ItineraryBuilder setCountry(String country) {
    itinerary.details.country = country;
    return this;
  }
  
  ItineraryBuilder setCity(String city) {
    itinerary.details.city = city;
    return this;
  }

  ItineraryBuilder setDate(String date) {
    itinerary.details.date = date;
    return this;
  }

  ItineraryBuilder setStartTime(String startTime) {
    itinerary.details.startTime = startTime;
    return this;
  }

  ItineraryBuilder setEndTime(String endTime) {
    itinerary.details.endTime = endTime;
    return this;
  }

  ItineraryBuilder setChosenAttractions(List<String> attractions) {
    itinerary.details.chosenAttractions = attractions;
    return this;
  }
  
  ItineraryBuilder setTransportationModes(List<String> transportationModes) {
    itinerary.details.transportationModes = transportationModes;
    return this;
  }

  ItineraryBuilder setEvents(List<Event> events) {
    itinerary.events = events;
    return this;
  }

  ItineraryBuilder setTimings(List<String> timings) {
    itinerary.timings = timings;
    return this;
  }

}