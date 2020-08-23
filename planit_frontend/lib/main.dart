import 'package:flutter/material.dart';

import 'package:planit_frontend/components/details.component.dart';
import 'package:planit_frontend/components/attractions.component.dart';
import 'package:planit_frontend/components/itinerary.component.dart';
import 'package:planit_frontend/components/previousItineraries.component.dart';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';
import 'package:planit_frontend/objects/itinerary/itineraryInfo.object.dart';
import 'package:planit_frontend/services/itinerary.service.dart'
    as ItineraryApi;

import 'components/createItinerary.component.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => MyAppState();
}

class MyAppState extends State<MyApp> {
  Widget build(BuildContext context) {
    return MaterialApp(
      home: LandingPage(),
      title: 'planit',
      theme: ThemeData(
        fontFamily: 'Varela Round',
        primaryColor: Colors.cyan[800],
        accentColor: Colors.green[200],
      ),
      debugShowCheckedModeBanner: false,
    );
  }
}

class LandingPage extends StatefulWidget {
  @override
  LandingPageState createState() => LandingPageState();
}

class LandingPageState extends State<LandingPage> {
  ItineraryComponent itineraryComponent;
  List<ItineraryInfo> prevIteraries;
  bool firstBuild = true;

  void refresh() {
    if (true) {
      if (firstBuild) getLastItinerary();
      firstBuild = false;
      setPreviousItineraries();
    }
  }

  @override
  Widget build(BuildContext context) {
    refresh();
    return new Scaffold(
        body: drawBody(),
        floatingActionButton: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.end,
            children: <Widget>[
              drawHistoryButton(),
              FloatingActionButton(
                heroTag: "FAB NEW ITINERARY",
                  onPressed: () {
                    _createItinerary(context);
                  },
                  backgroundColor: Colors.green[100],
                  child: Icon(Icons.add))
            ]));
  }

  Widget drawHistoryButton() {
    if (prevIteraries == null || prevIteraries.length < 2) {
      return Container();
    }

    return Padding(
        padding: EdgeInsets.only(left: 32),
        child: FloatingActionButton(
          heroTag: "FAB HISTORY",
            onPressed: () {
              Navigator.push(context,
              MaterialPageRoute(builder: (context) => PreviousItinerariesComponent(info: prevIteraries)));
            },
            backgroundColor: Colors.green[100],
            child: Icon(Icons.collections_bookmark)));
  }

  Widget drawBody() {
    // appbar wrapper
    return Container(
        color: Colors.white,
        child: Column(
          children: <Widget>[
            AppBar(
              title: Text("Your Itinerary"),
              backgroundColor: Colors.cyan[800],
            ),
            Expanded(child: drawMainBody())
          ],
        ));
  }

  Widget drawMainBody() {
    if (itineraryComponent == null) {
      // tell user to create an itinerary
      return Container(
          alignment: Alignment.center,
          child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Padding(
                    padding: EdgeInsets.only(bottom: 40),
                    child: Material(
                        color: Colors.white,
                        child: Text(
                          "Looks like you don't have any itineraries yet.",
                          textScaleFactor: 2,
                          textAlign: TextAlign.center,
                        ))),
                Material(
                    child: Text(
                  "Tap the plus button in the bottom right corner to create one.",
                  style: TextStyle(color: Colors.black),
                  textScaleFactor: 1,
                  textAlign: TextAlign.center,
                )),
              ]));
    } else {
      // display itinerary
      return itineraryComponent;
    }
  }

  _createItinerary(BuildContext context) async {
    final result = await Navigator.push(context,
        MaterialPageRoute(builder: (context) => CreateItineraryComponent()));
    if (result != null) {
      setState(() {
        itineraryComponent = result;
        print("itineraryComponent is $itineraryComponent");
        refresh();
      });
    }
  }

  getLastItinerary() async {
    Itinerary itinerary = await ItineraryApi.getMostRecentItinerary("1003429812");
    if (itinerary != null) {
      setState(() {
        itineraryComponent = ItineraryComponent(itinerary: itinerary);
      });
    }
  }

  setPreviousItineraries() async {
    List<ItineraryInfo> itineraries =
        await ItineraryApi.getPreviousItineraries("1003429812");
    setState(() {
      prevIteraries = itineraries;
    });
  }
}
