import 'dart:async';
import 'dart:convert';

import '../Global.dart';

//Future<List<String>> getAttractions() async {
//  // make the API call
//  var response = await Global.client.get('http://127.0.0.1:8000/v1/Attractions');
//
//  // decode the response body
//  /* example response body: { "attractions" : "att1, att2, ... , attn" } */
//  Map<String, dynamic> res = jsonDecode(response.body);
//  // parse the string and return the list
//  List<String> attractions = res["attractions"].split(",");
//  return attractions;
//}
Future<List<String>> getAttractions() async {
  // make the API call
  var response = await Global.client.get(Global.url+'/v1/attractions');
  // decode the response body
  /* example response body: { "attractions" : ["att1", "att2", ... , "attn"] } */
  Map<String, dynamic> res = jsonDecode(response.body);
  Iterable attractions = json.decode(res["attractions"]);
  List<String> mappedAttractions = attractions.map((a) => '$a').toList();
//  print(mappedAttractions[0]);
  return mappedAttractions;
}