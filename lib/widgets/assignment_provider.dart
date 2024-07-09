import 'package:flutter/material.dart';
import 'package:flutter/foundation.dart';

class Assignment {
  String name;
  DateTime deadline;
  bool completed;

  Assignment({
    required this.name,
    required this.deadline,
    this.completed = false,
  });
}

class AssignmentProvider with ChangeNotifier {
  List<Assignment> _assignments = [];
  List<Assignment> _filteredAssignments = [];

  List<Assignment> get notFinishedAssignments => _filteredAssignments
      .where((assignment) => !assignment.completed)
      .toList();

  List<Assignment> get finishedAssignments =>
      _filteredAssignments.where((assignment) => assignment.completed).toList();

  void addAssignment(String name, DateTime deadline) {
    _assignments.add(Assignment(name: name, deadline: deadline));
    _filteredAssignments = _assignments;
    notifyListeners();
  }

  void toggleAssignmentCompletion(Assignment assignment) {
    assignment.completed = !assignment.completed;
    notifyListeners();
  }

  void clearAssignments() {
    _assignments.clear();
    _filteredAssignments.clear();
    notifyListeners();
  }

  void filterAssignmentsByDate(DateTime date) {
    _filteredAssignments = _assignments.where((assignment) {
      return assignment.deadline.year == date.year &&
          assignment.deadline.month == date.month &&
          assignment.deadline.day == date.day;
    }).toList();
    notifyListeners();
  }

  int getAssignmentCount() {
    return _filteredAssignments.length;
  }

  void removeAssignments(int count) {
    if (count <= 0) return;
    _filteredAssignments =
        _filteredAssignments.sublist(0, _filteredAssignments.length - count);
    notifyListeners();
  }
}
