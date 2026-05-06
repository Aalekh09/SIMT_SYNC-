import 'package:flutter/material.dart';

class ReceptionDashboard extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Reception Panel"),
      ),
      body: Center(
        child: Text(
          "Welcome Reception",
          style: TextStyle(fontSize: 24),
        ),
      ),
    );
  }
}