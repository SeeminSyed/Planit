import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:planit_frontend/components/itinerary.component.dart';
import 'package:planit_frontend/components/previousItinerary.component.dart';
import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';
import 'package:planit_frontend/objects/itinerary/itineraryInfo.object.dart';
import 'package:planit_frontend/services/itinerary.service.dart'
    as ItineraryApi;

class PreviousItinerariesComponentState extends State<PreviousItinerariesComponent> {
   List<ItineraryInfo> info;
  
  PreviousItinerariesComponentState({Key key, @required this.info});

  @override
  Widget build(BuildContext context) {
    if (info != null) {
      print("itinerary loaded succesfully");
          return Scaffold(
            body: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>
                [
                  AppBar(
                    title: Text("Previously Made Itineraries") 
                  ),
                  drawInfo()
                ],
              ),
            ),
          
        );
    } else {
      print("itinerary failed to load");
      return new Container();
    }
  }

  Widget drawInfo() {
    return Expanded(
     child: ListView.separated(
        shrinkWrap: true,
        padding: const EdgeInsets.all(8),
        itemCount: info.length,
        itemBuilder: (BuildContext context, int index) {
          return Container(
            height: 80,
                child : Center(
                child:
                    ListTile(
                      contentPadding: EdgeInsets.only(left: 30),
                      title : Text(dateToString(info[index].getDate()), textScaleFactor: 1.2),
                      subtitle: 
                        Text(info[index].getCity() + ", " + info[index].getCountry(),
                        textScaleFactor: 1.1,
                        ),

                      onTap: () async {
                        Itinerary itinerary = await ItineraryApi.getItineraryByItineraryId(info[index].getId());
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => PreviousItineraryComponent(itinerary: itinerary)
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

  String dateToString(String date) {
    return new DateFormat.yMMMMEEEEd().format(
       DateFormat("dd-MM-yyyy", "en_US").parse(
                          date.substring(0,2) + "-" 
                          + date.substring(2,4) + "-"
                          + date.substring(4,8)
                        )).toString();
  }
}

class PreviousItinerariesComponent extends StatefulWidget {
  final List<ItineraryInfo> info;
  PreviousItinerariesComponent({Key key, @required this.info}) : super(key: key);
  
  @override
  State<PreviousItinerariesComponent> createState() => PreviousItinerariesComponentState(info: info);
}
