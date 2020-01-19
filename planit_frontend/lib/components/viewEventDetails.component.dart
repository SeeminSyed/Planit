

import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:planit_frontend/components/eventDetails.component.dart';
import 'package:planit_frontend/components/search.component.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:planit_frontend/components/similarEvents.component.dart';
import 'package:planit_frontend/components/searchBar.component.dart';
import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';
import 'package:smooth_star_rating/smooth_star_rating.dart';

class ViewEventDetailsScreen extends StatelessWidget {
  // Declare a field that holds the Todo.
  final Event event;
  final Itinerary itin;
  // var context;
  final searchQuery = TextEditingController();

  // In the constructor, require a Todo.
  ViewEventDetailsScreen({Key key, @required this.event, @required this.itin}) : super(key: key);

  // Edit this and draw event details
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          automaticallyImplyLeading: false,
          leading: IconButton(
              icon: Icon(Icons.arrow_back),
              onPressed: () => Navigator.pop(context, false)),
          title: Text(event.getName()),
          actions: <Widget>[
            Padding(padding: EdgeInsets.all(10), child: changeButton(context))
          ],
        ),
        body: EventDetailsScreen(event: event, itin: itin));
}

  Widget changeButton(BuildContext context) {
    return RaisedButton(
      child: Text("Change"),
      elevation: 10.0,
      color: Colors.green[100],
      onPressed: () => navigateToSubPage(context),
    );
  }
  /*
  the code underneath deals with displaying the rating of the event
  */

/*
  Widget ratingDisplay(BuildContext context, int rating) {
    return Row(
      mainAxisSize: MainAxisSize.min,
      children: List.generate(5, (index) {
        return IconTheme(
          data: IconThemeData(color: Colors.purple, size: 20,),
          child: Icon(index < rating ? Icons.star : Icons.star_border),
        );
      }),
    );
  }
  */
  navigateToSubPage(context) {
    //var todo1 = Todo(event.getId(), time, itin, event.getCost(), "");
    Navigator.push(context, MaterialPageRoute(builder: (context) => SearchComponent(event: event, itinerary: itin,)));
  }
}
