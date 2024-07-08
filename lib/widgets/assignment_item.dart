import 'package:flutter/material.dart';

class AssignmentItem extends StatelessWidget {
  final String assignmentName;
  final bool initialCompleted;
  final VoidCallback onToggleCompletion;
  final DateTime deadline;

  const AssignmentItem(
      {super.key,
      required this.assignmentName,
      required this.initialCompleted,
      required this.onToggleCompletion,
      required this.deadline});

  @override
  Widget build(BuildContext context) {
    final now = DateTime.now();
    return Container(
      margin: const EdgeInsets.only(bottom: 16),
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(12),
        boxShadow: [
          BoxShadow(
            color: Colors.grey.withOpacity(0.2),
            spreadRadius: 2,
            blurRadius: 5,
          ),
        ],
      ),
      child: Row(
        children: [
          GestureDetector(
            onTap: onToggleCompletion,
            child: Icon(
              initialCompleted ? Icons.cancel : Icons.check_circle,
              color: initialCompleted ? Colors.purple : Colors.purpleAccent,
            ),
          ),
          const SizedBox(
            width: 12,
          ),
          Text(
            assignmentName,
            style: const TextStyle(fontSize: 16),
          ),
          SizedBox(
            width: MediaQuery.of(context).size.width / 3 + 18,
          ),
          Text(
            "${deadline.month}/${deadline.day} ${deadline.hour}:${deadline.minute}",
            style: TextStyle(fontSize: 14, color: deadline.difference(now).inMinutes < 0 ? Colors.red : Colors.black26),
          ),
        ],
      ),
    );
  }
}
