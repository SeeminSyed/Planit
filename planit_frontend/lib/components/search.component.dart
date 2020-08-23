// TODO Implement this library.// Define a custom Form widget.
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:planit_frontend/components/changeEvent.component.dart';
import 'package:planit_frontend/objects/event/event.object.dart';
import 'package:planit_frontend/objects/event/eventInfo.object.dart';
import 'package:planit_frontend/objects/itinerary/itinerary.object.dart';
import 'package:planit_frontend/services/SearchBar.service.dart' as SearchApi;

class SearchComponentState extends State<SearchComponent>
    with AutomaticKeepAliveClientMixin {
  
  final Event event;
  TextEditingController searchBar = TextEditingController();
  List<EventInfo> eventInfo;
  bool firstLoad = true;
  Itinerary itinerary;

  SearchComponentState({Key key, @required this.event, @required this.itinerary});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          automaticallyImplyLeading: false,
          leading: IconButton(
            icon:Icon(Icons.arrow_back),
            onPressed:() => Navigator.pop(context, false)
          ),
          title: Text("Similar Events"),
        ),
        body: Column(
          children: <Widget>[
            Padding(
              padding: EdgeInsets.all(8),
              child: ListTile(
                title : Text (event.getName()),
                subtitle: Text(event.getAddress())
              )
            ),
            Divider(thickness: 1, color: Colors.black),
            drawSearchBar(),
            //Divider(thickness: 1, color: Colors.grey),
            drawFutureList(),
          ],
        )
    );
  }

  @override
  bool get wantKeepAlive => true;


  Widget drawSearchBar() {
    return Padding(
      padding: EdgeInsets.all(10),
        child:TextFormField(
      
          controller: searchBar,
          decoration: const InputDecoration(
            icon: Icon(Icons.search),
            hintText: "Search for an event..."
          ),
          keyboardType: TextInputType.text,
          onSaved: (value) => searchEvents(value),
      )
    );
  }

  Widget drawFutureList() {
    return FutureBuilder(
      builder: (context, projectSnap) {
        if (projectSnap.connectionState == ConnectionState.none &&
            projectSnap.hasData == null) {
          //print('project snapshot data is: ${projectSnap.data}');
          return Container();
        }
        return makeEvents();
    },
    future: searchEvents(searchBar.text),
  );
  }

  Widget makeEvents() {

    if (eventInfo == null) {
      return Center(child: new CircularProgressIndicator());
    }
    if (eventInfo.length <= 0) {
      return Expanded(
        child:Column(
          mainAxisAlignment: MainAxisAlignment.center,
          
          children: <Widget>[
            Padding(
              padding: EdgeInsets.only(bottom: 20),
              child: Text("Sorry, we couldn't find any similar events.", 
                textScaleFactor: 2.2,
                textAlign: TextAlign.center)
            ),
            drawNoInfoFoundSubtext()
          ]
        )
      );
    }
    return Expanded(
     child: ListView.separated(
        shrinkWrap: true,
        padding: const EdgeInsets.all(8),
        itemCount: eventInfo.length,
        itemBuilder: (BuildContext context, int index) {
          return Container(
            height: 80,
            child:
             Center(
                child:
                    ListTile(
                      
                      title : Text (eventInfo[index].getName()),
                      subtitle: Text(eventInfo[index].getAddress()),
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) {
                            return ChangeEvent(eventId: eventInfo[index].getId(), oldEvent: event, itin: this.itinerary);
                            }//
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

  Future<dynamic> searchEvents(String searchText) async {
    String searchText = searchBar.text;
    List<EventInfo> foundEvents = await SearchApi.getEvents(searchText, event);
    this.eventInfo = foundEvents;
    return foundEvents;
  }

  Widget drawNoInfoFoundSubtext() {
    if (searchBar.text != null && searchBar.text.length > 0) {
      return 
      Text(
        "Try broadening your search so we can give you more options.",
        textScaleFactor: 1.2,
        textAlign: TextAlign.center
      );
    }
    return Container();
  }
}

class SearchComponent extends StatefulWidget {

  Event event;
  Itinerary itinerary;
  SearchComponent({Key key, @required this.event, @required this.itinerary}) : super(key: key);
  @override
  SearchComponentState createState() => SearchComponentState(event: event, itinerary: itinerary);
}
