import 'package:flutter/material.dart';

class TeacherDashboard extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Teacher Panel"),
      ),
      body: Center(
        child: Text(
          "Welcome Teacher",
          style: TextStyle(fontSize: 24),
        ),
      ),
    );
  }
}