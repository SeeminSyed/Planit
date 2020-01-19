import 'package:flutter/material.dart';
import 'package:planit_frontend/components/createItinerary.component.dart';
import 'package:planit_frontend/services/locations.service.dart' as LocationAPI;

// https://stackoverflow.com/questions/53424916/textfield-validation-in-flutter

class Locations extends StatefulWidget {
  @override
  LocationState createState() => LocationState();
}

class LocationState extends State<Locations> {
  bool cityValidation = false;
  bool countryValidation = false;
  String city = "";
  String country = "";

  String validateCityCountry() {
    if (this.city != "" && this.country != "") {
      LocationAPI.apiLocationValidation(city, country).then((val) {
        // <------------------------------ ???
        print(
            "Country LocationAPI.apiLocationValidation(city, country).then: " +
                val.toString());
        if (val) {
          setState(() {
            cityValidation = true;
            countryValidation = true;
          });
          CreateItineraryComponentState.details.city = city;
          CreateItineraryComponentState.details.country = country;
          return null;
        }
        return "Please enter a valid location";
      });
    } else {
      setState(() {
        cityValidation = false;
        countryValidation = false;
      });
      return "Please enter a valid location";
    }
  }

  @override
  void dispose() {
//    _cityKey.dispose();
//    _countryKey.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    // make the attractions pretty here
    return Row(
      children: <Widget>[
        Flexible(
          child: TextFormField(
//            controller: _cityKey,
            decoration: InputDecoration(
              icon: Icon(Icons.location_city, size: 18.0),
              errorMaxLines: 3,
              labelText: 'City',
            ),
            onSaved: (value) {
              this.city = value;
            },
            validator: (value) {
              return validateCityCountry();
            },
          ),
        ),
//        SizedBox(
//          width: 5,
//          height: 5,
//        ),
        Flexible(
          child: TextFormField(
//            controller: _countryKey,
            decoration: InputDecoration(
              icon: Icon(
                Icons.location_city,
                size: 18.0,
              ),
//              errorStyle: TextStyle(fontSize: 8.0),
              errorMaxLines: 3,
              labelText: 'Country',
            ),
            onSaved: (value) {
              this.country = value;
            },
            validator: (value) {
              return validateCityCountry();
            },
          ),
        ),
      ],
    );
  }
}
