import 'package:http/http.dart' as http;
import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/event/eventImpl.object.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:planit_frontend/components/eventDetails.component.dart';
import 'package:planit_frontend/services/similarEvents.service.dart' as ChangeEventsAPI;
/*
class similarEventsScreen extends StatelessWidget{

  final Todo todo;
  List<Widget> buttonsList;
  similarEventsScreen({Key key, @required this.todo}): super(key: key);

  Future<List<ChangeEventsAPI.Venue>> callAPI() async {
    return ChangeEventsAPI.getEvents(todo.venueID);
  }

  @override
  Widget build(BuildContext context) {
    return
      Scaffold(
        appBar: AppBar(
          automaticallyImplyLeading: false,
          leading: IconButton(
            icon:Icon(Icons.arrow_back),
            onPressed:() => Navigator.pop(context, false),
          ),
        ),
        body: new FutureBuilder(
          future: callAPI(),
          builder: (BuildContext context, AsyncSnapshot<List<ChangeEventsAPI.Venue>> snapshot) {
            if (!snapshot.hasData){
              return new Container();
            }
            List<ChangeEventsAPI.Venue> content = snapshot.data;
            return new ListView.builder(
              itemCount: content.length,
              scrollDirection: Axis.vertical,
              itemBuilder: (BuildContext context, int index) {
                return RaisedButton(
                  onPressed: () {
                    int eventtime = 0;
                    for(int k = 0; k < todo.itinerary.getTimings().length; k++){
                      if(todo.itinerary.getTimings()[k] == todo.timing){
                        eventtime = k;
                      }
                    } 
                    Map<String, dynamic> data = {"id":todo.venueID, "name":content[index].name, "address":content[index].address,
                      "averageRating":4.0, "details":"", "contact":"", "cost":todo.cost};
                    Event newevent = new EventImpl(data);
                    todo.itinerary.getEvents()[eventtime] = newevent;
                    Navigator.pop(context);
                  },
                  child: Text("Name: " + content[index].name + "\n" + "Address: "+ content[index].address)
                );
              },
            );
          }  
        )
      );  
  }
}*/