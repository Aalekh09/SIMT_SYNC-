import 'package:flutter/material.dart';
import '../services/api_service.dart';

import 'admin_dashboard.dart';
import 'teacher_dashboard.dart';
import 'reception_dashboard.dart';
import 'student_dashboard.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {

  final email = TextEditingController();
  final password = TextEditingController();

  bool loading = false;

  void login() async {

    print("Login button clicked");

    setState(() {
      loading = true;
    });

    bool success = await ApiService.login(
      email.text.trim(),
      password.text.trim(),
    );

    print("Login Success: $success");
    print("Role: ${ApiService.role}");

    setState(() {
      loading = false;
    });

    if (!success) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text("Login Failed")),
      );
      return;
    }

    // 🔥 ROLE BASED ROUTING
    if (ApiService.role == "ADMIN") {

      print("Opening Admin Dashboard");

      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (_) => AdminDashboard()),
      );
    }

    else if (ApiService.role == "TEACHER") {

      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (_) => TeacherDashboard()),
      );
    }

    else if (ApiService.role == "RECEPTION") {

      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (_) => ReceptionDashboard()),
      );
    }

    else if (ApiService.role == "STUDENT") {

      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (_) => StudentDashboard()),
      );
    }

    else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text("Unknown Role")),
      );
    }
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(

      appBar: AppBar(
        title: const Text("SIMT Login"),
      ),

      body: SingleChildScrollView(

        child: Padding(
          padding: const EdgeInsets.all(20),

          child: Column(

            children: [

              const SizedBox(height: 60),

              const Icon(
                Icons.school,
                size: 100,
                color: Colors.indigo,
              ),

              const SizedBox(height: 20),

              const Text(
                "SIMT Sync",
                style: TextStyle(
                  fontSize: 32,
                  fontWeight: FontWeight.bold,
                  color: Colors.indigo,
                ),
              ),

              const SizedBox(height: 40),

              TextField(
                controller: email,

                decoration: const InputDecoration(
                  labelText: "Email",
                  border: OutlineInputBorder(),
                ),
              ),

              const SizedBox(height: 20),

              TextField(
                controller: password,
                obscureText: true,

                decoration: const InputDecoration(
                  labelText: "Password",
                  border: OutlineInputBorder(),
                ),
              ),

              const SizedBox(height: 30),

              SizedBox(
                width: double.infinity,
                height: 50,

                child: loading

                    ? const Center(
                        child: CircularProgressIndicator(),
                      )

                    : ElevatedButton(

                        onPressed: () {

                          print("BUTTON CLICKED");

                          login();
                        },

                        child: const Text(
                          "Login",
                          style: TextStyle(fontSize: 18),
                        ),
                      ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}