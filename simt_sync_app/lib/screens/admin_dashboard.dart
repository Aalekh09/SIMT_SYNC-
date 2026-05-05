import 'package:flutter/material.dart';
import 'add_user_screen.dart';

class AdminDashboard extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Admin Dashboard")),
      body: GridView.count(
        crossAxisCount: 2,
        padding: EdgeInsets.all(20),
        children: [
          dashboardCard(context, "Add Teacher", "TEACHER"),
          dashboardCard(context, "Add Reception", "RECEPTION"),
        ],
      ),
    );
  }

  Widget dashboardCard(BuildContext context, String title, String role) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (_) => AddUserScreen(role: role),
          ),
        );
      },
      child: Card(
        elevation: 5,
        child: Center(child: Text(title, style: TextStyle(fontSize: 18))),
      ),
    );
  }
}