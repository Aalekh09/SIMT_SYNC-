import 'dart:convert';
import 'package:http/http.dart' as http;

class ApiService {

  // ⚠️ CHANGE THIS (VERY IMPORTANT)
  static const String baseUrl = "http://192.168.0.102:8080";

  static Future<Map<String, dynamic>> login(String email, String password) async {
    final res = await http.post(
      Uri.parse("$baseUrl/auth/login"),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode({
        "email": email,
        "password": password,
      }),
    );

    return jsonDecode(res.body);
  }

  static Future<Map<String, dynamic>> getDashboard(String token) async {
    final res = await http.get(
      Uri.parse("$baseUrl/dashboard"),
      headers: {
        "Authorization": "Bearer $token",
      },
    );

    return jsonDecode(res.body);
  }
}