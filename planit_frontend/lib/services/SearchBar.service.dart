import 'dart:async';
import 'dart:convert';

import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/event/eventInfo.object.dart';

import '../Global.dart';

Future<List<EventInfo>> getEvents(String query, Event event) async {
  var params = {
    "query": query,
    "section": "topPicks", // event.getCategory(),
    "price": "1", //(event.getCost() ~/ 10).toString(),
    "near": "Toronto, Canada", //event.getAddress()
  };
  Uri uri = Uri.parse(Global.url + '/v1/Search');
  print(uri);
  final newURI = uri.replace(queryParameters: params);
  var res = await Global.client.get(newURI);
  List<EventInfo> eventInfo = getEventInfo(res.body);

// List<EventInfo> eventInfo = getEventInfo(_mockResponse());
  return eventInfo;
}

List<EventInfo> getEventInfo(String body) {
  if (body.isEmpty) {
    return new List<EventInfo>();
  }
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
  return "{" +
      "\"venues\": [" +
      "{ " +
      "\"id\":\"1\", " +
      "\"name\":\"Pizza Hut\", " +
      "\"address\":\"70 Clipper Rd\"" +
      "}, " +
      "{" +
      "\"id\":\"1\", " +
      "\"name\":\"Dominos\", " +
      "\"address\":\"College St\"" +
      "}, " +
      "{ " +
      "\"id\":\"1\", " +
      "\"name\":\"Pizza Pizza\", " +
      "\"address\":\"47 Markham RD\"" +
      "}, " +
      "{" +
      "\"id\":\"1\", " +
      "\"name\":\"Deep\", " +
      "\"address\":\"Address D\"" +
      "}, " +
      "{" +
      "\"id\":\"1\", " +
      "\"name\":\"Event E\", " +
      "\"address\":\"Address E\"" +
      "}, " +
      "{" +
      "\"id\":\"1\", " +
      "\"name\":\"Event F\", " +
      "\"address\":\"Address F\"" +
      "}, " +
      "{" +
      "\"id\":\"1\", " +
      "\"name\":\"Event G\", " +
      "\"address\":\"Address G\"" +
      "}, " +
      "{" +
      "\"id\":\"1\", " +
      "\"name\":\"Event H\", " +
      "\"address\":\"Address H\"" +
      "}, " +
      "{" +
      "\"id\":\"1\", " +
      "\"name\":\"Event I\", " +
      "\"address\":\"Address I\"" +
      "}, " +
      "{" +
      "\"id\":\"1\", " +
      "\"name\":\"Event J\", " +
      "\"address\":\"Address J\"" +
      "}"
          "]" +
      "}";
}
