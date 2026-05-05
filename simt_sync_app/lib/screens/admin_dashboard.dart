import 'package:flutter/material.dart';
import 'add_user_screen.dart';
import '../services/api_service.dart';

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
    var res = await ApiService.getDashboard();
    setState(() {
      data = res;
    });
  }

  Widget buildCard(String title, dynamic value, IconData icon, Color color) {
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(16),
        color: color,
        boxShadow: [BoxShadow(color: Colors.black12, blurRadius: 5)],
      ),
      padding: EdgeInsets.all(16),
      child: Row(
        children: [
          Icon(icon, size: 40, color: Colors.white),
          SizedBox(width: 15),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(title,
                  style: TextStyle(color: Colors.white, fontSize: 16)),
              Text(value.toString(),
                  style: TextStyle(
                      color: Colors.white,
                      fontSize: 22,
                      fontWeight: FontWeight.bold)),
            ],
          )
        ],
      ),
    );
  }

  void navigateTo(BuildContext context, Widget screen) {
    Navigator.pop(context); // close drawer

    Future.delayed(Duration(milliseconds: 200), () {
      Navigator.push(
        context,
        MaterialPageRoute(builder: (_) => screen),
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    if (data == null) {
      return Scaffold(
        body: Center(child: CircularProgressIndicator()),
      );
    }

    return Scaffold(
      appBar: AppBar(
        title: Text("Admin Dashboard"),
        backgroundColor: Colors.indigo,
      ),

      drawer: Drawer(
        child: Builder(
          builder: (context) => ListView(
            children: [
              DrawerHeader(
                decoration: BoxDecoration(color: Colors.indigo),
                child: Text("SIMT Admin",
                    style: TextStyle(color: Colors.white, fontSize: 20)),
              ),

              ListTile(
                leading: Icon(Icons.person_add),
                title: Text("Add Teacher"),
                onTap: () {
                  navigateTo(context, AddUserScreen(role: "TEACHER"));
                },
              ),

              ListTile(
                leading: Icon(Icons.person),
                title: Text("Add Reception"),
                onTap: () {
                  navigateTo(context, AddUserScreen(role: "RECEPTION"));
                },
              ),

              ListTile(
                leading: Icon(Icons.school),
                title: Text("Students"),
                onTap: () {
                  Navigator.pop(context);
                  ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(content: Text("Coming Soon")),
                  );
                },
              ),

              ListTile(
                leading: Icon(Icons.payment),
                title: Text("Fees"),
                onTap: () {
                  Navigator.pop(context);
                  ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(content: Text("Coming Soon")),
                  );
                },
              ),

              ListTile(
                leading: Icon(Icons.logout),
                title: Text("Logout"),
                onTap: () {
                  Navigator.pop(context);

                  Future.delayed(Duration(milliseconds: 200), () {
                    Navigator.pushReplacementNamed(context, "/login");
                  });
                },
              ),
            ],
          ),
        ),
      ),

      body: Padding(
        padding: const EdgeInsets.all(12),
        child: GridView.count(
          crossAxisCount: 2,
          crossAxisSpacing: 10,
          mainAxisSpacing: 10,
          children: [
            buildCard("Students", data!["totalStudents"],
                Icons.school, Colors.blue),
            buildCard("Collection", data!["totalCollection"],
                Icons.currency_rupee, Colors.green),
            buildCard("Revenue", data!["monthlyRevenue"],
                Icons.show_chart, Colors.orange),
            buildCard("Pending", data!["totalPending"],
                Icons.warning, Colors.red),
          ],
        ),
      ),
    );
  }
}