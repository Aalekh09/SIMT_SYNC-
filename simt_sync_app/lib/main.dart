import 'package:flutter/material.dart';
import 'screens/login_screen.dart';

void main() {
  runApp(const SimtApp());
}

class SimtApp extends StatelessWidget {
  const SimtApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'SIMT Sync',

      theme: ThemeData(
        primarySwatch: Colors.indigo,
        scaffoldBackgroundColor: Colors.grey[100],
        appBarTheme: const AppBarTheme(
          backgroundColor: Colors.indigo,
          centerTitle: true,
          elevation: 2,
        ),
      ),

      // ✅ ROUTES FIXED
      routes: {
        "/login": (context) => LoginScreen(),
      },

      home: LoginScreen(),
    );
  }
}