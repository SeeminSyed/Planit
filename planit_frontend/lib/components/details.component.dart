import 'package:flutter/material.dart';
import 'package:planit_frontend/components/dateTime.component.dart';
import 'package:planit_frontend/components/distance.component.dart';
import 'package:planit_frontend/components/locations.component.dart';
import 'package:planit_frontend/components/transportation.component.dart';

import 'createItinerary.component.dart';

// Define a custom Form widget.
class DetailsForm extends StatefulWidget {
  @override
  DetailsFormState createState() => DetailsFormState();
}

class DetailsFormState extends State<DetailsForm>
    with AutomaticKeepAliveClientMixin {
//  final _formKey = GlobalKey<FormState>();

  // this doesnt have a key. maybe it needs one, but god knows i dont know why it was yelling at me.
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Padding(
          padding: const EdgeInsets.fromLTRB(16.0, 8.0, 25.0, 4.0),
          child: Locations(),
        ),
        Padding(
            // num participants
            padding: const EdgeInsets.fromLTRB(16.0, 4.0, 25.0, 4.0),
            child: numParticipantsTextFormField()),
        Padding(
            // budget
            padding: const EdgeInsets.fromLTRB(16.0, 4.0, 25.0, 4.0),
            child: budgetTextFormField()),
        Padding(
          padding: const EdgeInsets.fromLTRB(16.0, 16.0, 16.0, 4.0),
          child: DateTimePicker(),
        ),
        Padding(
          // Transport
          padding: const EdgeInsets.fromLTRB(16.0, 0.0, 16.0, 0.0),
          child: SizedBox(
            height: 160.0,
            width: 500.0,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              mainAxisSize: MainAxisSize.max,
              children: <Widget>[
                Padding(
                  padding: const EdgeInsets.fromLTRB(8.0, 0.0, 16.0, 8.0),
                  child: Align(
                    alignment: Alignment.centerLeft,
                    child: Text(
                      "Transportation:",
                      style: TextStyle(color: Colors.cyan[800], fontSize: 18.0),
                      textAlign: TextAlign.left,
                    ),
                  ),
                ),
                Transportation(),
              ],
            ),
          ),
        ),
        Padding(
          // distance slider
          padding: const EdgeInsets.fromLTRB(16.0, 0.0, 16.0, 0.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[DistanceSlider()],
          ),
        ),
        SizedBox(
          height: 60.0,
        ),
      ],
    );
  }

  TextFormField numParticipantsTextFormField() {
    return TextFormField(
      // participants field
      decoration: const InputDecoration(
        icon: Icon(Icons.people, size: 18.0),
        labelText: 'Enter Number of Participants',
      ),
      keyboardType: TextInputType.number,
      onChanged: (value) => CreateItineraryComponentState
          .details.numberOfParticipants = int.parse(value),
      validator: (value) {
        if (value.isEmpty || !(value.contains(new RegExp(r"^[0-9]*$")))) {
          print("validating the entry in form");
          return 'Please enter a valid number of participants';
        }
        return null;
      },
    );
  }

  TextFormField budgetTextFormField() {
    return TextFormField(
      // budget field
      decoration: const InputDecoration(
        icon: Icon(Icons.monetization_on, size: 18.0),
        labelText: 'Enter Budget',
      ),
      keyboardType: TextInputType.number,
      onChanged: (value) =>
          CreateItineraryComponentState.details.maxBudget = int.parse(value),
      validator: (value) {
        if (value.isEmpty || !(value.contains(new RegExp(r"^[0-9]*$")))) {
          print("validating the entry in form");
          return 'Please enter a valid budget';
        }
        return null;
      },
    );
  }

  @override
  bool get wantKeepAlive => true;
}
