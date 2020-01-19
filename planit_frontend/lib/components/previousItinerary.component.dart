import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:planit_frontend/components/itinerary.component.dart';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';

class PreviousItineraryComponentState extends State<PreviousItineraryComponent> {
  final Itinerary itinerary;
  
  PreviousItineraryComponentState({Key key, @required this.itinerary});

  @override
  Widget build(BuildContext context) {
    if (itinerary != null) {
      print("itinerary loaded succesfully");
          return Scaffold(
            body: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>
                [
                  AppBar(
                    title: Text(dateToString(itinerary.getDate())) 
                  ),
                  Expanded(
                    child: ItineraryComponent(itinerary: itinerary)
                  )
                ],
              ),
            ),
          
        );
    } else {
      print("itinerary failed to load");
      return new Container();
    }
  }

  String dateToString(String date) {
    return new DateFormat.yMMMMEEEEd().format(
       DateFormat("dd-MM-yyyy", "en_US").parse(
                          date.substring(0,2) + "-" 
                          + date.substring(2,4) + "-"
                          + date.substring(4,8)
                        )).toString();
  }
}

class PreviousItineraryComponent extends StatefulWidget {
  final Itinerary itinerary;
  PreviousItineraryComponent({Key key, @required this.itinerary}) : super(key: key);
  
  @override
  State<PreviousItineraryComponent> createState() => PreviousItineraryComponentState(itinerary: itinerary);
}
