import 'package:flutter/material.dart';

import 'package:radiohead/screens/welcome_screen.dart';
import 'package:radiohead/theme/theme.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Radiohead',
      theme: lightMode,
      home: const WelcomeScreen(),
    );
  }
}

