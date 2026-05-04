import 'package:flutter/material.dart';

class StudentDashboard extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Student Dashboard")),
      body: Column(
        children: [

          Card(
            child: ListTile(
              title: Text("Name"),
              subtitle: Text("Student Name"),
            ),
          ),

          Card(
            child: ListTile(
              title: Text("Course"),
              subtitle: Text("DCA"),
            ),
          ),

          Card(
            child: ListTile(
              title: Text("Fees Status"),
              subtitle: Text("Pending ₹5000"),
            ),
          ),

        ],
      ),
    );
  }
}