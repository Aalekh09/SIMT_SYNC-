import 'package:flutter/material.dart';
import '../services/api_service.dart';
import 'admin_dashboard.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginScreen extends StatefulWidget {
  @override
  _LoginScreenState createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {

  final emailController = TextEditingController();
  final passwordController = TextEditingController();

  void login() async {
    var data = await ApiService.login(
      emailController.text,
      passwordController.text,
    );

    final prefs = await SharedPreferences.getInstance();
    await prefs.setString("token", data["token"]);
    await prefs.setString("role", data["role"]);

    if (data["role"] == "ADMIN") {
  Navigator.push(context,
    MaterialPageRoute(builder: (_) => AdminDashboard()));
} else if (data["role"] == "STUDENT") {
  Navigator.push(context,
    MaterialPageRoute(builder: (_) => StudentDashboard()));
}
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("SIMT Sync Login")),
      body: Padding(
        padding: EdgeInsets.all(20),
        child: Column(
          children: [
            TextField(controller: emailController, decoration: InputDecoration(labelText: "Email")),
            TextField(controller: passwordController, decoration: InputDecoration(labelText: "Password"), obscureText: true),
            SizedBox(height: 20),
            ElevatedButton(onPressed: login, child: Text("Login")),
          ],
        ),
      ),
    );
  }
}