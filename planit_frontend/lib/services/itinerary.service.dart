import 'dart:collection';
import 'dart:math';

import 'package:http/http.dart' as http;
import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/event/eventImpl.object.dart';
import 'dart:async';
import 'dart:convert';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';
import 'package:planit_frontend/objects/itinerary/itineraryBuilder.object.dart';
import 'package:planit_frontend/objects/itinerary/itineraryBuilderImpl.object.dart';
import 'package:planit_frontend/objects/itinerary/itineraryImpl.object.dart';
import 'package:planit_frontend/objects/itinerary/itineraryInfo.object.dart';
import 'package:planit_frontend/objects/itinerary/itneraryDetails.object.dart';

import '../Global.dart';

Future<Itinerary> getItinerary() async {
  return mockItinerary();
}

Future<Itinerary> getSuggestedItinerary(String userId, ItineraryDetails details) async {
  Map<String, dynamic> detailsJson = detailsToJson(userId, details);
  http.Response response = await Global.client.post(
    Global.url+'/v1/Itinerary',
    headers: {"Content-Type": "application/json"},
    body: jsonEncode(detailsJson)
  );
  print(Global.url+'/v1/Itinerary POST');
  print(jsonEncode(detailsJson));
  if (response.statusCode != 200 || response.body.isEmpty) {
    return new ItineraryBuilderImpl().buildEmpty(details);
  }
  Map<String, dynamic> res = jsonDecode(response.body); /*jsonDecode(mockResponse());*/
  print(response.body);
  return getItineraryFromJson(res);
}

Future<Itinerary> getMostRecentItinerary(String userId) async {
  
  var response = await Global.client.get(
    Global.url+'/v1/Itinerary?user_id='+userId,
    headers: {"Content-Type": "application/json"},
  );
  print(response.statusCode);
  print(Global.url+'/v1/Itinerary GET');
  Itinerary itinerary = getItineraryFromJson(jsonDecode(response.body));
  return itinerary;
}

Future<List<ItineraryInfo>> getPreviousItineraries(String userId) async {


  var response = await Global.client.get(
    Global.url+'/v1/Itinerary?user_id='+userId+'&all_id=true',
    headers: {"Content-Type": "application/json"},
  );

  Map<String, dynamic> result =  jsonDecode(response.body);
  List<dynamic> itineraries = result["itineraries"];
  List<ItineraryInfo> ret = new List<ItineraryInfo>();
  for (int i = 0; i < itineraries.length; i ++) {
    ret.add(new ItineraryInfo(itineraries[i]));
  }
  return ret;
}

Future<Itinerary> getItineraryByItineraryId(String itineraryId) async {
  var response = await Global.client.get(
    Global.url+'/v1/Itinerary?itinerary_id='+itineraryId,
    headers: {"Content-Type": "application/json"},
  );

  Itinerary itinerary = getItineraryFromJson(jsonDecode(response.body));
  return itinerary;
}

saveItinerary(String userId, Itinerary itinerary) async {
  var res = await Global.client.put(
    Global.url+'/v1/Itinerary',
    headers: {"Content-Type": "application/json"},
    body: jsonEncode(itineraryToJson(userId, itinerary))
  );

  print(res.statusCode);

}

Itinerary getItineraryFromJson(Map<String, dynamic> res) {
  // parse the string and return the list
  ItineraryBuilder builder = new ItineraryBuilderImpl();
  builder
  .setChosenAttractions(res["attractions"].split(","))
  .setCity(res["city"])
  .setCountry(res["country"])
  .setDate(res["date"])
  .setEndTime(res["endTime"])
  .setMaxBudget(res["maxBudget"])
  .setMaxDistance(res["maxDistance"])
  .setNumberOfParticipants(res["participants"])
  .setStartTime(res["startTime"])
  .setTransportationModes(res["transportation"].split(","))
  .setEvents(createEventList(res["events"]))
  .setTimings(createTimingList(res["events"]));
  return builder.build();
} 

List<Event> createEventList(List<dynamic> eventsAndTimings) {
  List<Event> events = new List();
  for (int i = 0; i < eventsAndTimings.length; i++) {
    events.add(EventImpl(eventsAndTimings[i]["event"]));
  }
  return events;
}

List<String> createTimingList(List<dynamic> eventsAndTimings) {
  List<String> timings = new List();
  
  for (int i = 0; i < eventsAndTimings.length; i++) {
    String time = eventsAndTimings[i]["eventStartTime"];
    /*
    String timeOfDay = "AM";
    int hour = int.parse(time.substring(0,2));

    if (hour >= 12) {
      timeOfDay = "PM";
      if (hour != 12) {
        hour = hour - 12;
      }
    } else if (hour == 0) {
      hour = 12;
    }*/
    timings.add(time);// hour.toString() +":" + time.substring(2,4) + " " + timeOfDay);
    
  }
  return timings;
}

Map<String, dynamic> itineraryToJson(String userId, Itinerary itinerary) {
  List<Event> events = itinerary.getEvents();
  List<String> timings = itinerary.getTimings();

  List<dynamic> eventList = new List();

  for (int i = 0; i < min(events.length, timings.length); i ++) {
    Map<String, dynamic> timing = new Map<String, dynamic>();
    timing["eventStartTime"] = timings[i];
    timing["eventEndTime"] = "2359";
    timing["totalTime"] = 0;
    

    Map<String, dynamic> event = new Map<String, dynamic>();
    event["eventId"] = events[i].getId();
    event["name"] = events[i].getName();
    event["address"] = events[i].getAddress();
    event["averageRating"] = events[i].getAverageRating();
    event["details"] = events[i].getDetails();
    event["contact"] = events[i].getContactInfo();
    event["category"] = "";
    event["cost"] = events[i].getCost();
    timing["event"] = event;

    eventList.add(timing);
  }

  Map<String, dynamic> itineraryJson = detailsToJson(userId, itinerary.getDetails());
  itineraryJson["events"] = eventList;
  return itineraryJson;
}

Map<String, dynamic> detailsToJson(String userId, ItineraryDetails details) {
  Map<String, dynamic> detailsJson = new Map<String, dynamic>();
  detailsJson["user_id"] = userId;
  detailsJson["transportation"] = details.transportationModes.join(",");
  detailsJson["participants"] = details.numberOfParticipants;
  detailsJson["maxDistance"] = details.maxDistance;
  detailsJson["maxBudget"] = details.maxBudget;
  detailsJson["country"] = details.country;
  detailsJson["city"] = details.city;
  detailsJson["date"] = details.date;
  detailsJson["startTime"] = details.startTime;
  detailsJson["endTime"] = details.endTime;
  detailsJson["duration"] = 12*60;
  detailsJson["attractions"] = details.chosenAttractions.join(",");
  return detailsJson;
}

Itinerary mockItinerary() {
  return getItineraryFromJson(jsonDecode(mockResponse()));
}

String multipleItineraryMockResponse() {
  return "{" +
    "\"itineraries\" : [ " + 
        "{ " +
            "\"id\": \"0\", " +
            "\"date\": \"A\", " +
            "\"city\": \"string\", " +
            "\"country\": \"string\" " +
        "}," +
        "{ " +
            "\"id\": \"0\", " +
            "\"date\": \"B\", " +
            "\"city\": \"string\", " +
            "\"country\": \"string\" " +
        "}," +
        "{ " +
            "\"id\": \"0\", " +
            "\"date\": \"C\", " +
            "\"city\": \"string\", " +
            "\"country\": \"string\" " +
        "}," +
        "{ " +
            "\"id\": \"0\", " +
            "\"date\": \"D\", " +
            "\"city\": \"string\", " +
            "\"country\": \"string\" " +
        "}," +
        "{ " +
            "\"id\": \"0\", " +
            "\"date\": \"E\", " +
            "\"city\": \"string\", " +
            "\"country\": \"string\" " +
        "}" +
    "]" +
  "}";
}

String mockResponse() {
  return
   "{ " +
    "\"transportation\": \"string\", " +
    "\"participants\": 0," +
    "\"maxDistance\": 0, " +
    "\"maxBudget\": 0, " +
    "\"country\": \"string\", " +
    "\"city\": \"string\", " +
    "\"date\": \"string\", " +
    "\"startTime\": \"0000\", " +
    "\"endTime\": \"0000\", " +
    "\"duration\": 0, " +
    "\"attractions\": \"string\", " +
    "\"events\": " +
		"[" +
			"{" +
				"\"eventStartTime\": \"0800\", " + // HHMM
				"\"eventEndTime\": \"0000\", " + // HHMM
				"\"totalTime\": 0, " +// in minutes
				"\"event\": " +
					"{" +
						"\"name\": \"string\", " +
						"\"address\":\"string\", " +
						"\"averageRating\": 0.0, " + // between 0 and 5
						"\"details\": \"string\", " +
            "\"cost\": 0, " +
						"\"contact\": \"string\" " +
					"}" +
			"}," +
      "{" +
				"\"eventStartTime\": \"1200\", " + // HHMM
				"\"eventEndTime\": \"0000\", " + // HHMM
				"\"totalTime\": 0, " +// in minutes
				"\"event\": " +
					"{" +
						"\"name\": \"string\", " +
						"\"address\":\"string\", " +
						"\"averageRating\": 0.0, " + // between 0 and 5
						"\"details\": \"string\", " +
            "\"cost\": 0, " +
						"\"contact\": \"string\" " +
					"}" +
			"}," +
      "{" +
				"\"eventStartTime\": \"1830\", " + // HHMM
				"\"eventEndTime\": \"0000\", " + // HHMM
				"\"totalTime\": 0, " +// in minutes
				"\"event\": " +
					"{" +
						"\"name\": \"string\", " +
						"\"address\":\"string\", " +
						"\"averageRating\": 0.0, " + // between 0 and 5
						"\"details\": \"string\", " +
            "\"cost\": 0, " +
						"\"contact\": \"string\" " +
					"}" +
			"}" +
		"]" +
  "}";
}