// Define a custom Form widget.
import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:planit_frontend/main.dart';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';
import 'package:planit_frontend/objects/itinerary/itneraryDetails.object.dart';
import 'package:planit_frontend/services/itinerary.service.dart'
    as ItineraryApi;

import 'createItinerary.component.dart';
import 'itinerary.component.dart';

class SuggestedItineraryComponentState extends State<SuggestedItineraryComponent> {
  ItineraryComponent _itinerary;
  bool generateItinerary = true;

  void callAPI() async {
    Itinerary itinerary = await ItineraryApi.getSuggestedItinerary("1003429812", CreateItineraryComponentState.details);
    if (mounted) {
      setState(() {
        _itinerary = ItineraryComponent(itinerary: itinerary);
      });
    }
    
  }

  @override
  Widget build(BuildContext context) {
    
    if (!CreateItineraryComponentState.details.validate()) {
      return unfinishedDetails();
    } 

    if (generateItinerary) {
      callAPI();
      generateItinerary = false;
    }

    if (_itinerary != null) {
      print("drawing suggested itinerary");
      if (_itinerary.itinerary.getEvents().length <= 0 || _itinerary.itinerary.getTimings().length <=0) {
        return emptyItinerary();
      } 
      return Scaffold(
        body: Center(
          child: _itinerary
        ),
        floatingActionButton: 
        FloatingActionButton(
          onPressed: () {

            ItineraryApi.saveItinerary("1003429812", _itinerary.itinerary);
            Navigator.pop(context, _itinerary);
            },
            backgroundColor: Colors.green[100],
            child: Icon(Icons.check)
          ),
        );
    } else {
      print("drawing placeholder container");
      return new Container(child: Center(child: CircularProgressIndicator()));
    }
  }
      
    Widget unfinishedDetails() {
      return Container(
          alignment: Alignment.center,
          child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                  
                Padding(
                    padding: EdgeInsets.all(40),
                    child: Material(
                        child: Text(
                          "Please finish filling in the details on the previous pages.",
                          textScaleFactor: 1.1,
                          textAlign: TextAlign.center,
                        )
                      )
                    ),
              ]
            )
          );
    }

    Widget emptyItinerary() {
      return Container(
          alignment: Alignment.center,
          child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                  
                Padding(
                    padding: EdgeInsets.all(10),
                    child: Material(
                        child: Text(
                           "Sorry, we couldn't find any events for you.",
                          textScaleFactor: 1.7,
                          textAlign: TextAlign.center,
                        )
                      )
                    ),
                    Padding(
                    padding: EdgeInsets.all(10),
                    child: Material(
                        child: Text(
                           "Try changing your details and attractions, then come back to this page to try again.",
                          textScaleFactor: 1,
                          textAlign: TextAlign.center,
                        )
                      )
                    ),
              ]
            )
          );
    }

}

class SuggestedItineraryComponent extends StatefulWidget {

  @override
  SuggestedItineraryComponentState createState() => SuggestedItineraryComponentState();
}
