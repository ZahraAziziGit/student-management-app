import 'package:flutter/material.dart';

import 'package:radiohead/widgets/custom_scaffold.dart';
import 'package:radiohead/screens/user_info_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return CustomScaffold(
      child: Column(
        children: [
          Center(
            child: ElevatedButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (e) => const UserInfoScreen(),
                  ),
                );
              },
              child: const Text("User Info"),
            ),
          ),
        ],
      ),
    );
  }
}
