
import 'dart:async';
import 'dart:convert';

import '../Global.dart';

/*
Returns the list of transportation modes using Transportation API.
*/
Future<List<String>> getTransportation() async {
  // Make the API call.
  var response =
      await Global.client.get(Global.url+'/v1/Transportation');
  /* Decode the response body.
   Example response body: {"transportation" : "mode1, mode2, ... , moden"} 
   */
  Map<String, dynamic> res = jsonDecode(response.body);
  // Parse the string and return the list.
  List<String> transportation = res["transportation"].split(",");
  return transportation;
}

/* Returns the list of (hardcoded) transportation modes.
  Note: Method was created for testing purposes in transportation.component.dart. 
*/ 
List<String> getTransportationMock() {
  List<String> transportation = ["Walking", "Driving", "Taking public transit"];
  return transportation;
}
