

import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:planit_frontend/components/search.component.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:planit_frontend/components/similarEvents.component.dart';
import 'package:planit_frontend/components/searchBar.component.dart';
import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';
import 'package:smooth_star_rating/smooth_star_rating.dart';

class EventDetailsScreen extends StatelessWidget {
  // Declare a field that holds the Todo.
  final Event event;
  final Itinerary itin;
  // var context;
  final searchQuery = TextEditingController();

  // In the constructor, require a Todo.
  EventDetailsScreen({Key key, @required this.event, @required this.itin}) : super(key: key);

  // Edit this and draw event details
  @override
  Widget build(BuildContext context) {
    // initializing controller and marker for the map
    Completer<GoogleMapController> _controller = Completer();
    LatLng location = LatLng(43.6619552, -79.410376);
    Set<Marker> markers = new Set<Marker>();
    markers.add(Marker(
      markerId: MarkerId(location.toString()),
      position: location,
      infoWindow: InfoWindow(
        title: event.getName(),
        snippet: event.getAddress(),
      ),
      icon: BitmapDescriptor.defaultMarker,
    ));
    // Use the Todo to create the UI.
    return Scaffold(
        body: Padding(
          padding: EdgeInsets.all(16.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Expanded(child: details(context, event)),
              Expanded(
                  child: GoogleMap(
                      mapType: MapType.normal,
                      initialCameraPosition: CameraPosition(
                          target: location, zoom: 15.0),
                      onMapCreated: (GoogleMapController controller) {
                        _controller.complete(controller);
                      },
                      markers: markers))
            
            ],
          ),
        ));
}

  Widget details(BuildContext context, Event event) {
    return new ListView.builder(
        itemCount: 6,
        scrollDirection: Axis.vertical,
        itemBuilder: (BuildContext context, int index) {
          if (index == 0) {
            return Text("Name: " + event.getName().toString() + "\n",
                textAlign: TextAlign.left);
          } else if (index == 1) {
            return Text("Address: " + event.getAddress() + "\n",
                textAlign: TextAlign.left);
          } else if (index == 2) {
            return SmoothStarRating(
              allowHalfRating: true,
              starCount: 5,
              rating: 2.4,
              size: 20.0,
              color: Colors.amber,
              borderColor: Colors.black,
              spacing: 0.0
            );
          } else if (index == 3) {
            return Text("Cost: " + event.getCost().toString() + "\n",
                textAlign: TextAlign.left);
          } else if (index == 4) {
            return Text("Description: \n" + event.getDetails() + "\n",
                textAlign: TextAlign.left);
          }
          if (index == 5) {
            return Text("Contact Information: " + event.getContactInfo() + "\n",
                textAlign: TextAlign.left);
          }
          if (index == 6) {
            return Text("MAP", textAlign: TextAlign.left);
          }
        });
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
}
