class EventInfo {
  String _id;
  String _address;
  String _name;

  EventInfo (Map<String, dynamic> res) {
    this._id = res["id"];
    this._address = res["address"];
    this._name = res["name"];
  }

  String getId() {
    return _id;
  }

  String getAddress() {
    return _address;
  }

  String getName(){
    return _name;
  }

  Map<String, dynamic> toJson() => {
        "id": _id,
        "name": _name,
        "address": _address,
    };

}