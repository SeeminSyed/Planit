import 'package:planit_frontend/objects/event/event.object.dart';

class EventImpl implements Event{
  String id;
  String name;
  String address;
  double averageRating;
  String details;
  String contactInfo;
  int cost;
  String category;

  EventImpl(Map<String, dynamic> data) {
    id = data["eventId"];
    name = data["name"];
    address = data["address"];
    averageRating = data["averageRating"].toDouble();
    details = data["details"];
    contactInfo = data["contact"];
    cost = data["cost"];
    category = data["category"];
  }

  String getId() {
    return id;
  }

  String getName() {
    return name;
  }

  String getAddress() {
    return address;
  }

  double getAverageRating() {
    return averageRating;
  }

  String getDetails() {
    return details;
  }

  String getContactInfo() {
    return contactInfo;
  }

  int getCost() {
    return cost;
  }

  String getCategory() {
    return category;
  }
}