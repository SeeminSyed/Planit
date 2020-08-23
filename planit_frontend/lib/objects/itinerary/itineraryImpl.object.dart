import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/itinerary/itneraryDetails.object.dart';

import 'itinerary.object.dart';

class ItineraryImpl implements Itinerary{

  ItineraryDetails details;
  List<String> timings;
  List<Event> events;

  ItineraryImpl() {
    details = new ItineraryDetails();
  }

  @override
  List<String> getChosenAttractions() {
    return details.getChosenAttractions();
  }

  @override
  String getCity() {
    return details.getCity();
  }

  @override
  String getCountry() {
    return details.getCountry();
  }

  @override
  String getDate() {
    return details.getDate();
  }

  @override
  String getEndTime() {
    return details.getEndTime();
  }

  @override
  List<Event> getEvents() {
    return events;
  }

  List<String> getTiming(){
    return timings;
  }

  @override
  int getMaxBudget() {
    return details.getMaxBudget();
  }

  @override
  int getMaxDistance() {
    return details.getMaxDistance();
  }

  @override
  int getNumberOfParticipants() {
    return details.getNumberOfParticipants();
  }

  @override
  String getStartTime() {
    return details.getStartTime();
  }

  @override
  List<String> getTransportationModes() {
    return details.getTransportationModes();
  }

  @override
  List<String> getTimings() {
    return timings;
  }

  @override
  ItineraryDetails getDetails() {
    return details;
  }

  @override
  void replaceEvent(Event oldEvent, Event newEvent) {
    events[events.indexWhere((x) => x.getId() == oldEvent.getId())] = newEvent;
  }

  
}