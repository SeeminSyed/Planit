// Define a custom Form widget.
import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:planit_frontend/components/eventDetails.component.dart';
import 'package:planit_frontend/components/viewEventDetails.component.dart';
import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';
import 'package:planit_frontend/services/changeEvent.service.dart';

class ItineraryComponentState extends State<ItineraryComponent>
    with AutomaticKeepAliveClientMixin {
  final Itinerary itinerary;
  
  ItineraryComponentState({Key key, @required this.itinerary});

  @override
  Widget build(BuildContext context) {
    if (itinerary != null) {
      print("itinerary loaded succesfully");
          return Scaffold(
            body: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[makeEvents()],
              ),
            ),
          
        );
    } else {
      print("itinerary failed to load");
      return new Container();
    }
  }

  @override
  bool get wantKeepAlive => itinerary != null;

  Widget makeEvents() {
    List<String> timings = itinerary.getTimings();
    List<Event> events = itinerary.getEvents();
    return Expanded(
     child: ListView.separated(
        shrinkWrap: true,
        padding: const EdgeInsets.all(8),
        itemCount: min(timings.length, events.length),
        itemBuilder: (BuildContext context, int index) {
          return Container(
            height: 100,
            child: Center(
                child:
                    ListTile(
                      leading:  Container(
                        alignment: Alignment.center,
                        margin: EdgeInsets.symmetric(),

                        width: 80,
                        child: 
                          Text(
                            timings[index],
                            textAlign: TextAlign.center,
                            textScaleFactor: 1.2
                            )
                          ),
                      title : Text (events[index].getName()),
                      subtitle: Text(events[index].getAddress()),
                    onTap: () async {
                      Event event = events[index];
                      Event detailedEvent = await getEventFromId(events[index].getId());
                      if (detailedEvent != null) {
                        event = detailedEvent;
                      }
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => ViewEventDetailsScreen(event: event, itin: this.itinerary)
                        )
                      );

                    },
                    )
                  )
            );
          },
          separatorBuilder: (BuildContext context, int index) => const Divider(color: Color.fromARGB(100, 0, 0, 0))
        )
      );
  }

  Widget drawEvent(String timing, Event event) {
    return ListTile(
      
      leading: Text(timing),
      title : Text (event.toString()),
      

    );
  }
}

class ItineraryComponent extends StatefulWidget {
  final Itinerary itinerary;
  ItineraryComponent({Key key, @required this.itinerary}) : super(key: key);
  @override
  ItineraryComponentState createState() => ItineraryComponentState(itinerary: itinerary);
}
