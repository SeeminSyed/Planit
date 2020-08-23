
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:planit_frontend/components/eventDetails.component.dart';
import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';
import 'package:planit_frontend/services/changeEvent.service.dart';

class ChangeEvent extends StatelessWidget {
  // Declare a field that holds the Todo.
  Event event;
  final String eventId;
  final Event oldEvent;
  final Itinerary itin;
  final searchQuery = TextEditingController();

  // In the constructor, require a Todo.
  ChangeEvent({Key key, @required this.eventId, @required this.oldEvent, @required this.itin}) : super(key: key);

  // Edit this and draw event details
  @override
  Widget build(BuildContext context) {
    // Use the Todo to create the UI.
    return 
    FutureBuilder(
      builder: (context, projectSnap) {
        if (projectSnap.connectionState == ConnectionState.none &&
            projectSnap.hasData == null) {
          //print('project snapshot data is: ${projectSnap.data}');
          return Container();
        }
        if (event == null) {
          return Center(child: Container( child: CircularProgressIndicator() ));
        }
         return Scaffold(
          appBar: AppBar(
          automaticallyImplyLeading: false,
          leading: IconButton(
            icon:Icon(Icons.arrow_back),
            onPressed:() => Navigator.pop(context, false)
          ),
          title: Text(event.getName()),
          actions: <Widget>[
            Padding(
              padding: EdgeInsets.all(10),
              child:RaisedButton(
                child: Text("Confirm"),
                elevation: 10.0,
                color: Colors.green[100],
                onPressed: (){
                  itin.replaceEvent(oldEvent, event);
                  int i = 3;
                  Navigator.of(context)
                  .popUntil((route) {
                     if (i <= 0) {
                       return true;
                     } else {
                       i --;
                       return false;
                     }
                  } );
                },
            )
            )
          ],
        ),
        body: EventDetailsScreen(event: event, itin: itin)
        );

        },
      future: setEvent()
      );
  }

  Future<Event> setEvent() async {
    Event event = await getEventFromId(eventId);
    if (event == null) {
      event = mockEvent();
    }
    this.event = event;
    return event;
  }

  
}
