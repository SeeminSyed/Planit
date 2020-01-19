import 'dart:convert';
import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/event/eventImpl.object.dart';

import '../Global.dart';

Future<Event> getEventFromId(String eventId) async {
  var params = {"venue_id" : eventId };
  Uri uri = Uri.parse(Global.url+'/v1/ChangeEvent');
  final newURI = uri.replace(queryParameters: params);
  var res = await Global.client.get(newURI);
  if (res.statusCode != 200) return null;
  Event event = EventImpl(jsonDecode(res.body)); //getEventInfo(_mockResponse());
  return event;
}

Event mockEvent() {
  return new EventImpl(jsonDecode(mockEventBody()));
}

String mockEventBody() {
  return 
					"{" +
            "\"eventId\": \"string\", " +
						"\"name\": \"string\", " +
						"\"address\":\"string\", " +
						"\"averageRating\": 0.0, " + // between 0 and 5
						"\"details\": \"string\", " +
            "\"cost\": 0, " +
						"\"contact\": \"string\" " +
					"}";
}