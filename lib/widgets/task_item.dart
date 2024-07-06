import 'package:flutter/material.dart';

class TaskItem extends StatelessWidget {
  final String taskName;
  final bool initialCompleted;
  final VoidCallback onToggleCompletion;

  const TaskItem({super.key,
    required this.taskName,
    required this.initialCompleted,
    required this.onToggleCompletion,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onToggleCompletion,
      child: Container(
        margin: const EdgeInsets.only(bottom: 16),
        padding: const EdgeInsets.all(16),
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(8),
          boxShadow: [
            BoxShadow(
              color: Colors.grey.withOpacity(0.2),
              spreadRadius: 2,
              blurRadius: 5,
            ),
          ],
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              taskName,
              style: const TextStyle(fontSize: 16),
            ),
            Icon(
              initialCompleted ? Icons.cancel : Icons.check_circle,
              color: initialCompleted ? Colors.purple : Colors.purpleAccent,
            ),
          ],
        ),
      ),
    );
  }
}
