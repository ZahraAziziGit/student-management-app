import 'package:flutter/material.dart';

class TaskItemWithTime extends StatelessWidget {
  final String taskName;
  final bool initialCompleted;
  final VoidCallback onToggleCompletion;
  final VoidCallback onToggleDelete;
  final DateTime deadline;

  const TaskItemWithTime(
      {super.key,
      required this.taskName,
      required this.initialCompleted,
      required this.onToggleCompletion,
      required this.onToggleDelete,
      required this.deadline});

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.only(bottom: 16),
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(20),
        boxShadow: [
          BoxShadow(
            color: Colors.grey.withOpacity(0.2),
            spreadRadius: 2,
            blurRadius: 5,
          ),
        ],
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          Text(
            taskName,
            style: const TextStyle(fontSize: 16),
          ),
          Text(
            "${deadline.month}/${deadline.day} ${deadline.hour}:${deadline.minute}",
            style: const TextStyle(fontSize: 14, color: Colors.black38),
          ),
          Row(
            children: [
              GestureDetector(
                onTap: onToggleCompletion,
                child: Icon(
                  initialCompleted ? Icons.cancel : Icons.check_circle,
                  color: initialCompleted ? Colors.purple : Colors.purpleAccent,
                ),
              ),
              const SizedBox(
                width: 10,
              ),
              GestureDetector(
                onTap: onToggleDelete,
                child: const Icon(
                  Icons.delete,
                  color: Colors.indigo,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
