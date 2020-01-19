import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:planit_frontend/services/transportation.service.dart'
    as TransportationAPI;

import 'createItinerary.component.dart';

class TransportationState extends State<Transportation> {
  List<String> _transportation = List<String>();
  final Set<String> chosenTransportation = Set<String>();

  void callAPI() {
    //_transportation = await TransportationAPI.getTransportation();
    setState(() {
      _transportation = TransportationAPI.getTransportationMock();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(child: makeTable());
  }

  Widget makeTable() {
    callAPI();
    return Expanded(
      child: _transportation.length > 0
          ? ListView.builder(
              physics: NeverScrollableScrollPhysics(),
              // Display all available transportation modes with checkboxes.
              itemCount: _transportation.length, // # of modes in the list.
              itemBuilder: (_, index) {
                return makeRow(_transportation[index]);
              })
          // Display an error message if no transportation methods can be found.
          : Text("No transportation methods found..."),
    );
  }

  Widget makeRow(String transportation) {
    final bool alreadyChosen = chosenTransportation.contains(transportation);
    return Padding(
      padding: const EdgeInsets.only(left: 16),
      child: Center(
        heightFactor: 0.6,
        child: CheckboxListTile(
          activeColor: Colors.green[100],
          title: Text(transportation, style: TextStyle(color: Colors.grey[700])),
          value: alreadyChosen,
          onChanged: (bool value) {
            setState(() {
              if (alreadyChosen) {
                chosenTransportation.remove(transportation);
              } else {
                chosenTransportation.add(transportation);
              }
              CreateItineraryComponentState.details.transportationModes =
                  chosenTransportation.toList();
              print(chosenTransportation);
            });
          },
        ),
      ),
    );
  }
}

class Transportation extends StatefulWidget {
  @override
  TransportationState createState() => TransportationState();
}
