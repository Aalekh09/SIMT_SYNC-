import 'dart:convert';
import 'package:http/http.dart' as http;

class ApiService {
  static const String baseUrl = "http://192.168.0.102:8080";

  static String? token;

  // LOGIN
  static Future<bool> login(String email, String password) async {
    final res = await http.post(
      Uri.parse("$baseUrl/auth/login"),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode({
        "email": email,
        "password": password,
      }),
    );

    if (res.statusCode == 200) {
      final data = jsonDecode(res.body);
      token = data["token"];
      return true;
    }
    return false;
  }

  static Future<Map<String, dynamic>> getDashboard() async {
  final res = await http.get(
    Uri.parse("$baseUrl/dashboard"),
    headers: {
      "Authorization": "Bearer $token"
    },
  );

  return jsonDecode(res.body);
}

  // ADD USER (TEACHER / RECEPTION)
  static Future<bool> addUser(
    String name, String email, String password, String role) async {

  print("Sending data:");
  print("Name: $name");
  print("Email: $email");
  print("Password: $password");
  print("Role: $role");

  final bodyData = jsonEncode({
    "name": name,
    "email": email,
    "password": password,
    "role": role
  });
  print("TOKEN: $token");
  print("JSON: $bodyData"); // 🔥 IMPORTANT

  final res = await http.post(
    Uri.parse("$baseUrl/users/add"),
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer $token"
    },
    body: bodyData,
  );

  print("Response: ${res.statusCode}");
  print("Response Body: ${res.body}");

  return res.statusCode == 200;
}
}