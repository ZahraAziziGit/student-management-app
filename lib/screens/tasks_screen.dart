import 'package:flutter/material.dart';

import 'package:radiohead/widgets/task_item.dart';
import 'package:radiohead/widgets/navigation_bar.dart';

import 'package:radiohead/screens/home_screen.dart';

class TasksScreen extends StatefulWidget {
  const TasksScreen({Key? key}) : super(key: key);

  @override
  State<TasksScreen> createState() => _TasksScreenState();
}

class _TasksScreenState extends State<TasksScreen> {
  int _selectedIndex = 1;

  final List<Widget> _widgetOptions = <Widget>[
    const HomeContent(),
    const TasksContent(),
    const Text('Classes Page'),
    const Text('News Page'),
    const Text('Assignments Page'),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });

    if (index == 0) {
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => const HomeScreen())
      );
    }

    if (index == 1) {
      Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => const TasksScreen())
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        leading: null,
      ),
      extendBodyBehindAppBar: true,
      body: Stack(
        children: [
          // Background Image
          Container(
            decoration: const BoxDecoration(
              image: DecorationImage(
                image: AssetImage("assets/images/purple-pattern.jpg"),
                fit: BoxFit.cover,
              ),
            ),
          ),
          // Content
          _widgetOptions[_selectedIndex],
        ],
      ),
      bottomNavigationBar: CustomBottomNavigationBar(
        currentIndex: _selectedIndex,
        onTap: _onItemTapped,
      ),
    );
  }
}

class TasksContent extends StatefulWidget {
  const TasksContent({Key? key}) : super(key: key);

  @override
  _TasksContentState createState() => _TasksContentState();
}

class _TasksContentState extends State<TasksContent> {
  final List<Task> _currentTasks = [
    Task(name: 'ODE - HW1', completed: false),
    Task(name: 'AP - HW2', completed: false),
  ];

  final List<Task> _doneTasks = [
    Task(name: 'AP - HW1', completed: true),
    Task(name: 'DM - HW1', completed: true),
  ];

  void _toggleTaskCompletion(Task task) {
    setState(() {
      if (task.completed) {
        _doneTasks.remove(task);
        _currentTasks.add(task);
      } else {
        _currentTasks.remove(task);
        _doneTasks.add(task);
      }
      task.completed = !task.completed;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: ListView(
        children: [
          const SizedBox(height: 18),
          const Text(
            'Current tasks',
            textAlign: TextAlign.left,
            style: TextStyle(
                fontSize: 18, fontWeight: FontWeight.bold, color: Colors.white),
          ),
          const SizedBox(height: 12),
          Column(
            children: _currentTasks
                .map((task) => TaskItem(
              taskName: task.name,
              initialCompleted: task.completed,
              onToggleCompletion: () => _toggleTaskCompletion(task),
            ))
                .toList(),
          ),
          const SizedBox(height: 12),
          const Text(
            'Done tasks',
            textAlign: TextAlign.left,
            style: TextStyle(
                fontSize: 18, fontWeight: FontWeight.bold, color: Colors.white),
          ),
          const SizedBox(height: 12),
          Column(
            children: _doneTasks
                .map((task) => TaskItem(
              taskName: task.name,
              initialCompleted: task.completed,
              onToggleCompletion: () => _toggleTaskCompletion(task),
            ))
                .toList(),
          ),
        ],
      ),
    );
  }
}

class Task {
  String name;
  bool completed;

  Task({required this.name, this.completed = false});
}
