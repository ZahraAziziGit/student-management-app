import 'package:flutter/material.dart';

class SummaryItem extends StatelessWidget {
  final IconData icon;
  final String text;

  const SummaryItem({super.key, required this.icon, required this.text});

  @override
  Widget build(BuildContext context) {
    double heightOfScreen = MediaQuery.of(context).size.height;
    double widthOfScreen = MediaQuery.of(context).size.width;
    return Container(
      width: widthOfScreen / 4,
      height: 120,
      padding: const EdgeInsets.all(10),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(10),
        boxShadow: [
          BoxShadow(
            color: Colors.grey.withOpacity(0.3),
            spreadRadius: 2,
            blurRadius: 10,
          ),
        ],
      ),
      child: Column(
        children: [
          const SizedBox(
            height: 5,
          ),
          Icon(icon, size: 30, color: Colors.deepPurpleAccent),
          const SizedBox(height: 10),
          Text(
            text,
            textAlign: TextAlign.center,
            style: const TextStyle(fontSize: 12, fontFamily: 'Georgia'),
          ),
        ],
      ),
    );
  }
}
