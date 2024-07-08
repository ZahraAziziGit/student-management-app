import 'package:flutter/material.dart';

class Assignment {
  String name;
  bool completed;
  DateTime deadline;

  Assignment({required this.name, this.completed = false, required this.deadline});
}

final DateTime now = DateTime.now();

class AssignmentProvider with ChangeNotifier {
  final List<Assignment> _notFinishedAssignments = [
    Assignment(name: 'AP - HW2', completed: false, deadline: DateTime.parse('2024-07-20 20:18')),
  ];

  final List<Assignment> _finishedAssignments = [
    Assignment(name: 'AP - HW1', completed: true, deadline: DateTime.parse('2024-07-02 15:09')),
    Assignment(name: 'DM - HW1', completed: true, deadline: DateTime.parse('2024-07-16 23:59')),
  ];

  List<Assignment> get notFinishedAssignments => _notFinishedAssignments;
  List<Assignment> get finishedAssignments => _finishedAssignments;

  void toggleAssignmentCompletion(Assignment assignment) {
    if (assignment.completed) {
      _finishedAssignments.remove(assignment);
      _notFinishedAssignments.add(assignment);
    } else {
      _notFinishedAssignments.remove(assignment);
      _finishedAssignments.add(assignment);
    }
    assignment.completed = !assignment.completed;
    notifyListeners();
  }
}
