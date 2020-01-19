import 'package:http/http.dart' as http;

class Global {
  static final client = new http.Client();
  static final url = "http://10.0.2.2:8080";
}