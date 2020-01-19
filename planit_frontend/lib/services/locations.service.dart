
import 'dart:async';
import 'dart:convert';

import '../Global.dart';

Future<bool> apiLocationValidation(String city, String country) async {
//  return true;
  var response = await Global.client.get(
    Global.url+'/v1/validateLocation?city=$city&country=$country',
    headers: {"Content-Type": "application/json"},
  );
  print(Global.url+'/v1/validateLocation?city=$city&country=$country');
  /* example response body: {"result": [true or false]} */
  Map<String, dynamic> res = jsonDecode(response.body);
  // parse the string and return the list
  bool validLocation = res["result"];
  return validLocation;
}

bool retFal(String city, String country) {
  return false;
}

bool retTrue(String city, String country) {
  return true;
}
