import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import 'createItinerary.component.dart';

// https://api.flutter.dev/flutter/material/showDatePicker.html
// https://github.com/flutter/flutter/blob/master/examples/flutter_gallery/lib/demo/material/date_and_time_picker_demo.dart
// https://www.youtube.com/watch?v=8sFE8IQyv_c

class DateTimePicker extends StatefulWidget {
  @override
  _State createState() => _State();
}

class _State extends State<DateTimePicker> {
  DateTime _date = new DateTime(
      DateTime.now().year, DateTime.now().month, DateTime.now().day + 1);

  DateTime datePicked = new DateTime(
      DateTime.now().year, DateTime.now().month, DateTime.now().day + 1);

  TimeOfDay startTimePicked = new TimeOfDay.now();

  TimeOfDay endTimePicked =
      TimeOfDay(hour: TimeOfDay.now().hour + 1, minute: TimeOfDay.now().minute);

  _State() {
    CreateItineraryComponentState.details.date = formatToMMDDYYYY(datePicked);
    CreateItineraryComponentState.details.startTime =
        formatToHHMM(startTimePicked);
    CreateItineraryComponentState.details.endTime = formatToHHMM(endTimePicked);
  }

//  bool startTimeChanged = false;
  bool invalidEnd = false;

  Future<Null> _selectDate(BuildContext context) async {
    final DateTime picked = await showDatePicker(
        context: context,
        firstDate: _date,
        initialDate: datePicked,
        lastDate: new DateTime(DateTime.now().year + 1));

    if (picked != null && picked != datePicked) {
      print('date selected: ${DateFormat.yMMMd().format(datePicked)}');
      setState(() {
        datePicked = picked;
        CreateItineraryComponentState.details.date =
            formatToMMDDYYYY(datePicked);
      });
    } else {
      return null;
    }
  }

  Future<Null> _selectStartTime(BuildContext context) async {
    final TimeOfDay picked =
        await showTimePicker(context: context, initialTime: startTimePicked);
    if (picked != null && picked != startTimePicked) {
      print('time selected: ${(startTimePicked.format(context))}');
      setState(() {
        startTimePicked = picked;
        CreateItineraryComponentState.details.startTime =
            formatToHHMM(startTimePicked);
      });
      if (endTimePicked.hour - startTimePicked.hour < 1) {
        setState(() {
          endTimePicked = TimeOfDay(
              hour: startTimePicked.hour + 1, minute: startTimePicked.minute);
        });
      }
      setState(() {
        invalidEnd = false;
      });
    }
  }

  Future<Null> _selectEndTime(BuildContext context) async {
    final TimeOfDay picked =
        await showTimePicker(context: context, initialTime: endTimePicked);

    if (picked != null && picked != endTimePicked) {
      if (picked.hour - startTimePicked.hour < 1) {
        setState(() {
          invalidEnd = true;
        });
      } else {
        print('time selected: ${(endTimePicked.format(context))}');
        setState(() {
          endTimePicked = picked;
          CreateItineraryComponentState.details.endTime =
              formatToHHMM(endTimePicked);
        });
        setState(() {
          invalidEnd = false;
        });
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Center(
        child: Column(
//          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          mainAxisSize: MainAxisSize.max,
          children: <Widget>[
            RaisedButton(
//              shape: RoundedRectangleBorder(
//                  borderRadius: BorderRadius.circular(5.0)),
              elevation: 1.0,
              color: Colors.white,
              textColor: Colors.cyan[800],
              onPressed: () {
                _selectDate(context);
              },
              child: Padding(
                padding: const EdgeInsets.fromLTRB(0.0, 10.0, 0.0, 10.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  mainAxisSize: MainAxisSize.max,
                  children: <Widget>[
                    Row(
                      children: <Widget>[
                        Container(
                          child: Row(
                            children: <Widget>[
                              Icon(
                                Icons.date_range,
                                size: 18.0,
//                              color: Colors.cyan[800],
                              ),
                              Text(
                                "   Date",
                                style: TextStyle(
//                                  color: Colors.cyan[800],
//                                  fontWeight: FontWeight.bold,
                                    fontSize: 18.0),
                              ),
                            ],
                          ),
                        )
                      ],
                    ),
                    Text(
                      DateFormat.yMMMd().format(datePicked),
                      style: TextStyle(
//                        color: Colors.cyan[800],
//                        fontWeight: FontWeight.bold,
                          fontSize: 18.0),
                    ),
                  ],
                ),
              ),
            ),
            RaisedButton(
//              shape: RoundedRectangleBorder(
//                  borderRadius: BorderRadius.circular(5.0)),
              elevation: 1.0,
              color: Colors.white,
              onPressed: () {
//                setState(() {
//                  startTimeChanged = true;
//                });
                _selectStartTime(context);
              },
              textColor: Colors.cyan[800],
              child: Padding(
                padding: const EdgeInsets.fromLTRB(0.0, 10.0, 0.0, 10.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  mainAxisSize: MainAxisSize.max,
                  children: <Widget>[
                    Row(
                      children: <Widget>[
                        Container(
                          child: Row(
                            children: <Widget>[
                              Icon(
                                Icons.access_time,
                                size: 18.0,
//                              color: Colors.cyan[800],
                              ),
                              Text(
                                "   Start Time",
                                style: TextStyle(
//                                  color: Colors.cyan[800],
//                                  fontWeight: FontWeight.bold,
                                    fontSize: 18.0),
                              ),
                            ],
                          ),
                        )
                      ],
                    ),
                    Text(
                      " ${(startTimePicked.format(context))}",
                      style: TextStyle(
//                        color: Colors.cyan[800],
//                        fontWeight: FontWeight.bold,
                          fontSize: 18.0),
                    ),
                  ],
                ),
              ),
            ),
            RaisedButton(
//              shape: RoundedRectangleBorder(
//                  borderRadius: BorderRadius.circular(5.0)),
              elevation: 1.0,
              color: Colors.white,
//              onPressed: !startTimeChanged
//                  ? null
//                  : () {
//                      _selectEndTime(context);
//                    },
            onPressed: () {_selectEndTime(context);},
              disabledColor: Colors.grey[100],
              disabledTextColor: Colors.grey,
              textColor: Colors.cyan[800],
              child: Padding(
                padding: const EdgeInsets.fromLTRB(0.0, 10.0, 0.0, 10.0),
                child: Row(
                  mainAxisSize: MainAxisSize.max,
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Row(
                      children: <Widget>[
                        Container(
                          child: Row(
                            children: <Widget>[
                              Icon(
                                Icons.access_time,
                                size: 18.0,
//                              color: Colors.cyan[800],
                              ),
                              Text(
                                "   End Time",
                                style: TextStyle(
//                                  color: Colors.cyan[800],
//                                  fontWeight: FontWeight.bold,
                                    fontSize: 18.0),
                              ),
                            ],
                          ),
                        )
                      ],
                    ),
                    Text(
                      " ${(endTimePicked.format(context))}",
                      style: TextStyle(
//                        color: Colors.cyan[800],
//                        fontWeight: FontWeight.bold,
                          fontSize: 18.0),
                    ),
                  ],
                ),
              ),
            ),
            SizedBox(
              height: 4,
            ),
            Padding(
              padding: const EdgeInsets.only(left: 40.0),
              child: Row(
                children: <Widget>[
                  Text(
                    invalidEnd
                        ? 'Itinerary must be at least an hour long'
                        : ' ',
                    textAlign: TextAlign.left,
                    overflow: TextOverflow.visible,
                    style: TextStyle(color: Colors.red[800], fontSize: 12.0),
                  ),
                ],
              ),
            )
          ],
        ),
      );
  }

  String formatToMMDDYYYY(DateTime datePicked) {
    String month = datePicked.month.toString().length == 1
        ? "0" + datePicked.month.toString()
        : datePicked.month.toString();
    String day = datePicked.day.toString().length == 1
        ? "0" + datePicked.day.toString()
        : datePicked.day.toString();

    return month + day + datePicked.year.toString();
  }

  String formatToHHMM(TimeOfDay tod) {
    int pickedHour = tod.hour;
    String hour = pickedHour.toString().length == 1
        ? "0" + pickedHour.toString()
        : pickedHour.toString();

    int pickedMinute = tod.minute;
    String minute = pickedMinute.toString().length == 1
        ? "0" + pickedMinute.toString()
        : pickedMinute.toString();

    return hour + minute;
  }
}
