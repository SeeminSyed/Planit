class ItineraryDetails {
  List<String> chosenAttractions;
    int numberOfParticipants;
    int maxDistance;
    int maxBudget;
    String country;
    String city;
    String date;
    String startTime;
    String endTime;
    List<String> transportationModes;

     
  List<String> getChosenAttractions() {
    return chosenAttractions;
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

  
  String getEndTime() {
    return endTime;
  }
  
  int getMaxBudget() {
    return maxBudget;
  }

  
  int getMaxDistance() {
    return maxDistance;
  }

  
  int getNumberOfParticipants() {
    return numberOfParticipants;
  }

  
  String getStartTime() {
    return startTime;
  }

  
  List<String> getTransportationModes() {
    return transportationModes;
  }
/*
  String toString() {
    return "Country: " + country + "\nCity: " + city + "\ntransportation options selected: "
                + transportationModes.toString() + "\ndate: " + date + "\nstartTime: " + startTime + "\nendTime: "
                + endTime + "\nparticipants: " + numberOfParticipants.toString() + "\nmaxBudget: " + maxBudget.toString()
                + "\nmaxDistance: " + maxDistance.toString() + "\nchosenAttractions: " + chosenAttractions.toString();
  }*/

  bool validate() {
    try {
      return 
      chosenAttractions.length > 0
      && numberOfParticipants > 0
      && maxBudget > 0
      && maxDistance > 0
      && city != null
      && country != null
      && date != null 
      && startTime != null
      && endTime != null
      && transportationModes.length > 0;
    }
    catch (Exception){
      return false;
    }
  }
}