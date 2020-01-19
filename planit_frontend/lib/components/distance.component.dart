import 'package:flutter/material.dart';
import 'package:planit_frontend/components/createItinerary.component.dart';

class DistanceSlider extends StatefulWidget {

  @override
  _DistanceSliderState createState() => _DistanceSliderState();
}

class _DistanceSliderState extends State<DistanceSlider> {
  int sliderValue = 1; // 1 km is minimum distance the user can pick for (max) distance.

  @override
  Widget build(BuildContext context) {
    CreateItineraryComponentState.details.maxDistance = sliderValue;
    return Container(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Align(
              alignment: Alignment.centerLeft,
              child: Container(
                  child: Text(
                "Maximum distance (in kilometres): $sliderValue KM", // Message is displayed to user so they know what they've picked.
                style: TextStyle(color: Colors.cyan[800], fontSize: 18.0),
                textAlign: TextAlign.left,
              )),
            ),
          ),
          Container(
            child: Align(
              child: Container(
                child: Slider(
                  min: 1,
                  max: 50,
                  value: sliderValue.roundToDouble(),
                  activeColor: Colors.cyan[800],
                  inactiveColor: Colors.green[100],
                  onChanged: (newValue) {
                    setState(() {
                      sliderValue = newValue.round(); // Distance must be an integer between 1 and 50 (both inclusive).
                      CreateItineraryComponentState.details.maxDistance = sliderValue;
                    });
                  },
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
