import 'package:flutter/material.dart';
import 'screens/login_screen.dart';

void main() {
  runApp(SimtApp());
}

class SimtApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'SIMT Sync',
      theme: ThemeData(primarySwatch: Colors.indigo),
      home: LoginScreen(),
    );
  }
}