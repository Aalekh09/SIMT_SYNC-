import 'package:flutter/material.dart';
import '../services/api_service.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AdminDashboard extends StatefulWidget {
  @override
  _AdminDashboardState createState() => _AdminDashboardState();
}

class _AdminDashboardState extends State<AdminDashboard> {

  Map<String, dynamic>? data;

  @override
  void initState() {
    super.initState();
    loadDashboard();
  }

  void loadDashboard() async {
    final prefs = await SharedPreferences.getInstance();
    String token = prefs.getString("token") ?? "";

    var res = await ApiService.getDashboard(token);

    setState(() {
      data = res;
    });
  }

  Widget card(String title, dynamic value) {
    return Card(
      elevation: 5,
      child: Padding(
        padding: EdgeInsets.all(16),
        child: Column(
          children: [
            Text(title),
            SizedBox(height: 10),
            Text("$value", style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
          ],
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {

    if (data == null) {
      return Scaffold(
        body: Center(child: CircularProgressIndicator()),
      );
    }

    return Scaffold(
      appBar: AppBar(title: Text("Admin Dashboard")),
      body: GridView.count(
        crossAxisCount: 2,
        children: [
          card("Students", data!["totalStudents"]),
          card("Collection", data!["totalCollection"]),
          card("Revenue", data!["monthlyRevenue"]),
          card("Pending", data!["totalPending"]),
        ],
      ),
    );
  }
}