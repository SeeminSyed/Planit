import 'package:flutter/material.dart';
import 'package:planit_frontend/components/attractions.component.dart';
import 'package:planit_frontend/components/details.component.dart';
import 'package:planit_frontend/components/suggestedItinerary.component.dart';
import 'package:planit_frontend/objects/itinerary/itneraryDetails.object.dart';

class CreateItineraryComponentState extends State<CreateItineraryComponent>
    with SingleTickerProviderStateMixin {
  static ItineraryDetails details;
  TabController _tabController;
  bool _rightVisible = true;
  bool _leftVisible = false;
  final _detailsFormKey = GlobalKey<FormState>();
  bool shouldValidate = false;

  final List<Tab> myTabs = <Tab>[
    // details
    Tab(
      icon: Icon(Icons.subject, size: 15),
      text: 'Details',
    ),
    // attractions
    Tab(
      icon: Icon(Icons.local_activity, size: 15),
      text: 'Attractions',
    ),
    // itinerary
    Tab(
      icon: Icon(Icons.account_balance, size: 15),
      text: 'Itinerary',
    ),
  ];

  void _nextTab() {
    switch (_tabController.index) {
      case 0:
//        if (_detailsFormKey.currentState.validate()) {
        _tabController.animateTo(_tabController.index + 1);
//        setState(() {
//          _leftVisible = true;
//        });
//        }
        break;
      case 1:
        _tabController.animateTo(_tabController.index + 1);
//        setState(() {
//          _rightVisible = false;
//        });
        break;
    }
  }

  void _backTab() {
    switch (_tabController.index) {
      case 2:
        _tabController.animateTo(_tabController.index - 1);
//        setState(() {
//          _rightVisible = true;
//        });
        break;
      case 1:
        _tabController.animateTo(_tabController.index - 1);
//        setState(() {
//          _leftVisible = false;
//        });
        break;
    }
  }

  void _handleTabChange() {
    shouldValidate = !shouldValidate;
    if (shouldValidate) {
      _detailsFormKey.currentState.save();
      //Code for handling tab change. For example you could call changeState() or access other members of the parent class in needed.
      switch (_tabController.index) {
        case 0:
          setState(() {
            _leftVisible = false;
            _rightVisible = true;
          });
          break;
        case 1:
          if (_tabController.previousIndex == 0) {
            if (_detailsFormKey.currentState.validate()) {
              print("valid");
              setState(() {
                _rightVisible = true;
                _leftVisible = true;
              });
            } else {
              // 0 to 1 and false then kick back
              print("invalid");
              _tabController.animateTo(_tabController.index - 1);
            }
          } else if (_tabController.previousIndex == 2) {
            setState(() {
              _leftVisible = true;
              _rightVisible = true;
            });
          }
          print("invalid");
//        _nextTab();
          break;
        case 2:
          if (_tabController.previousIndex == 0) {
            if (_detailsFormKey.currentState.validate()) {
              print("valid");
              setState(() {
                _leftVisible = true;
                _rightVisible = false;
              });
            } else {
              print("invalid");
              _tabController.animateTo(_tabController.index - 2);
            }
          } else if (_tabController.previousIndex == 1) {
            setState(() {
              _leftVisible = true;
              _rightVisible = false;
            });
          }
          print("invalid");
          break;
      }
    }
  }

  @override
  void initState() {
    super.initState();
    _tabController = TabController(vsync: this, length: myTabs.length);
    _tabController.addListener(_handleTabChange);
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  CreateItineraryComponentState() : super() {
    details = new ItineraryDetails();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          bottom: TabBar(
            unselectedLabelColor: Colors.green[100],
            labelColor: Colors.cyan[800],
            indicator: BoxDecoration(color: Colors.green[100]),
            controller: _tabController,
            tabs: myTabs,
          ),
          title: Text('Create An Itinerary')),
      body: TabBarView(
        controller: _tabController,
        physics: NeverScrollableScrollPhysics(),
        children: [
          Container(
            color: Colors.white,
            child: SingleChildScrollView(
                child: Form(
//                    autovalidate: true,
                    key: _detailsFormKey,
//                    onChanged: () {
//                      _detailsFormKey.currentState.validate();
//                    },
                    child: DetailsForm())),
          ),
          Attractions(),
          SuggestedItineraryComponent(),
        ],
      ),
      floatingActionButton: Padding(
        padding: const EdgeInsets.fromLTRB(30.0, 0.0, 0.0, 0.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          mainAxisSize: MainAxisSize.max,
          children: <Widget>[
            Visibility(
              visible: _leftVisible,
              child: new FloatingActionButton(
                  mini: true,
                  heroTag: "BACK",
                  tooltip: "Back",
                  onPressed: () {
                    _backTab();
                  },
                  backgroundColor: Colors.green[100],
                  child: Icon(
                    Icons.keyboard_arrow_left,
//                    size: 50.0,
                    color: Colors.cyan[800],
                  )),
            ),
            Visibility(
              visible: _rightVisible,
              child: new FloatingActionButton(
                  mini: true,
                  heroTag: "NEXT",
                  tooltip: "Next",
                  onPressed: () {
                    _nextTab();
                  },
                  backgroundColor: Colors.green[100],
                  child: Icon(
                    Icons.keyboard_arrow_right,
//                  size: 50.0,
                    color: Colors.cyan[800],
                  )),
            ),
          ],
        ),
      ),
    );
  }
}

class CreateItineraryComponent extends StatefulWidget {
  @override
  CreateItineraryComponentState createState() =>
      CreateItineraryComponentState();
}
