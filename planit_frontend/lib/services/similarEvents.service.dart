
import 'package:planit_frontend/objects/event/event.object.dart';
import 'dart:async';
import 'dart:convert';

import 'package:planit_frontend/objects/event/eventInfo.object.dart';

import '../Global.dart';

Future<List<EventInfo>> getEvents(String query, Event event) async {
  var params = {
      "query": query, 
      "section": event.getCategory(),
      "price": (event.getCost() ~/ 10).toString(),
      "near": event.getAddress()
  };

  Uri uri = Uri.parse(Global.url+'/v1/Search');
  final newURI = uri.replace(queryParameters: params);
  var res = await Global.client.get(newURI);
  List<EventInfo> eventInfo = getEventInfo(res.body); //getEventInfo(_mockResponse());
  return eventInfo;
}

List<EventInfo> getEventInfo(String body){

    Map<String, dynamic> res = jsonDecode(body);
    List<dynamic> venues = res["venues"];
    if (venues == null || venues.length == 0) {
      return new List<EventInfo>();
    }
    //List<EventInfo> result = List<EventInfo>();
    //for (int i = 0; i < venues.length; i ++ ) { result.add(EventInfo(venues[i])); }
    return List<EventInfo>.from(venues.map((x) => EventInfo(x)));
  }

  String _mockResponse() {
    return 
    "{" +
      "\"venues\": [" +
        "{ "+
          "\"id\":\"1\", " +
          "\"name\":\"Event A\", " +
          "\"address\":\"Address A\""+
        "}, "+
        "{" +
          "\"id\":\"1\", " +
          "\"name\":\"Event B\", " +
          "\"address\":\"Address B\""+
        "}, " +
        "{ " +
          "\"id\":\"1\", " +
          "\"name\":\"Event C\", " +
          "\"address\":\"Address C\""+
        "}, "+
        "{" +
          "\"id\":\"1\", " +
          "\"name\":\"Event D\", " +
          "\"address\":\"Address D\""+
        "}, " +
        "{" +
          "\"id\":\"1\", " +
          "\"name\":\"Event E\", " +
          "\"address\":\"Address E\""+
        "}, " +
        "{" +
          "\"id\":\"1\", " +
          "\"name\":\"Event F\", " +
          "\"address\":\"Address F\""+
        "}, " +
        "{" +
          "\"id\":\"1\", " +
          "\"name\":\"Event G\", " +
          "\"address\":\"Address G\""+
        "}, "+
        "{" +
          "\"id\":\"1\", " +
          "\"name\":\"Event H\", " +
          "\"address\":\"Address H\""+
        "}, " +
        "{" +
          "\"id\":\"1\", " +
          "\"name\":\"Event I\", " +
          "\"address\":\"Address I\""+
        "}, "+
        "{" +
          "\"id\":\"1\", " +
          "\"name\":\"Event J\", " +
          "\"address\":\"Address J\""+
        "}"
      "]" +
    "}";
  }
