class ItineraryInfo {
    
  String id;
  String country;
  String city;
  String date;

  ItineraryInfo(Map<String, dynamic> res) {
    id = res["id"];
    city = res["city"];
    country = res["country"];
    date = res["date"];
  }

  String getId() {
    return id;
  }

  String getCity() {
    return city;
  }

  String getCountry() {
    return country;
  }

  String getDate() {
    return date;
  }
}