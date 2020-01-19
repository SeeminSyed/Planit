import 'package:flutter/material.dart';
import 'package:planit_frontend/components/createItinerary.component.dart';
import 'package:planit_frontend/services/attractions.service.dart'
    as AttractionAPI;

class AttractionState extends State<Attractions>
    with AutomaticKeepAliveClientMixin {
  final Set<String> _chosenAttractions = Set<String>();

  @override
  Widget build(BuildContext context) {
    // make the attractions pretty here
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[makeTable()],
        ),
      ),
    );
  }

  Widget makeTable() {
    return FutureBuilder(
      future: AttractionAPI.getAttractions(),
      builder: (_, AsyncSnapshot<List<String>> attractions) {
        if (attractions.connectionState == ConnectionState.none &&
            attractions.hasData == null) {
          return CircularProgressIndicator();
        } else if (attractions.hasData) {
          return Expanded(
              child: SizedBox(
                  height: 200.0,
                  child: ListView.builder(
                      // create a list and show them
                      itemCount:
                          attractions.data.length, // num items in the list
                      itemBuilder: (_, index) {
                        return makeRow(attractions.data[index]);
                      })));
        } else {
          return Column(
            children: <Widget>[
              CircularProgressIndicator(),
            ],
          );
        }
      },
    );
  }

  Widget makeRow(String attraction) {
    final bool alreadyChosen = _chosenAttractions.contains(attraction);
//    return ListTile(
//      title: Text(attraction),
//      leading: Checkbox(
//        value: alreadyChosen,
//        onChanged: (bool value) {
//          setState(() {
//            if (alreadyChosen) {
//              _chosenAttractions.remove(attraction);
//            } else {
//              _chosenAttractions.add(attraction);
//            }
//            CreateItineraryComponentState.details.chosenAttractions =
//                _chosenAttractions.toList();
//            print(_chosenAttractions);
//          });
//        },
//      ),
//    );
    return Center(
      heightFactor: 1,
      child: CheckboxListTile(
        activeColor: Colors.green[100],
        title: Text(attraction, style: TextStyle(fontSize: 18.0)),
        value: alreadyChosen,
        onChanged: (bool value) {
          setState(() {
            if (alreadyChosen) {
              _chosenAttractions.remove(attraction);
            } else {
              _chosenAttractions.add(attraction);
            }
            CreateItineraryComponentState.details.chosenAttractions =
                _chosenAttractions.toList();
            print(_chosenAttractions);
          });
        },
      ),
    );
  }

  @override
  bool get wantKeepAlive => true;
}

class Attractions extends StatefulWidget {
  @override
  AttractionState createState() => AttractionState();
}
