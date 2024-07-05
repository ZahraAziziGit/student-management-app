import 'package:flutter/material.dart';

class CustomBottomNavigationBar extends StatelessWidget {
  final int currentIndex;
  final ValueChanged<int> onTap;

  const CustomBottomNavigationBar({
    required this.currentIndex,
    required this.onTap,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return BottomNavigationBar(
      type: BottomNavigationBarType.fixed,
      items: const <BottomNavigationBarItem>[
        BottomNavigationBarItem(
          icon: Icon(Icons.home),
          label: 'Home',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.task),
          label: 'Tasks',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.school),
          label: 'Classes',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.campaign),
          label: 'News',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.assignment),
          label: 'Homework',
        ),
      ],
      currentIndex: currentIndex,
      selectedItemColor: Colors.deepPurpleAccent,
      onTap: onTap,
    );
  }
}
