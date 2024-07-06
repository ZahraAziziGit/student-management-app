import 'package:flutter/material.dart';

class Task {
  String name;
  bool completed;
  DateTime deadline;

  Task({required this.name, this.completed = false, required this.deadline});
}

class TaskProvider with ChangeNotifier {
  final List<Task> _currentTasks = [
    Task(name: 'ODE - HW1', completed: false, deadline: DateTime.parse('2024-08-12 12:20')),
    Task(name: 'AP - HW2', completed: false, deadline: DateTime.parse('2024-07-20 20:18')),
  ];

  final List<Task> _doneTasks = [
    Task(name: 'AP - HW1', completed: true, deadline: DateTime.parse('2024-07-02 15:09')),
    Task(name: 'DM - HW1', completed: true, deadline: DateTime.parse('2024-07-16 23:59')),
  ];

  List<Task> get currentTasks => _currentTasks;
  List<Task> get doneTasks => _doneTasks;

  void toggleTaskCompletion(Task task) {
    if (task.completed) {
      _doneTasks.remove(task);
      _currentTasks.add(task);
    } else {
      _currentTasks.remove(task);
      _doneTasks.add(task);
    }
    task.completed = !task.completed;
    notifyListeners();
  }

  void deleteTask(Task task) {
    if (task.completed) {
      _doneTasks.remove(task);
    } else {
      _currentTasks.remove(task);
    }
    notifyListeners();
  }

  void addTask(String name, DateTime deadline) {
    _currentTasks.add(Task(name: name, deadline: deadline));
    notifyListeners();
  }
}
