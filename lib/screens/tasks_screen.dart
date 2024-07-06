import 'package:flutter/material.dart';

import 'package:provider/provider.dart';

import 'package:radiohead/widgets/task_provider.dart';
import 'package:radiohead/widgets/task_item_with_time.dart';
import 'package:radiohead/widgets/navigation_bar.dart';

import 'package:radiohead/screens/home_screen.dart';

class TasksScreen extends StatefulWidget {
  const TasksScreen({super.key});

  @override
  State<TasksScreen> createState() => _TasksScreenState();
}

class _TasksScreenState extends State<TasksScreen> {
  int _selectedIndex = 1;
  DateTime _selectedDate = DateTime.now();
  TimeOfDay _selectedTime = TimeOfDay.now();

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
          context, MaterialPageRoute(builder: (context) => const HomeScreen()));
    }

    if (index == 1) {
      Navigator.push(context,
          MaterialPageRoute(builder: (context) => const TasksScreen()));
    }
  }

  Future<void> _selectDate(BuildContext context) async {
    final DateTime? picked = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(2024),
      lastDate: DateTime(2025),
    );
    if (picked != null) {
      setState(() {
        _selectedDate = picked;
      });
    }
  }

  Future<void> _selectTime(BuildContext context) async {
    final TimeOfDay? picked = await showTimePicker(
      context: context,
      initialTime: TimeOfDay.now(),
    );
    if (picked != null) {
      setState(() {
        _selectedTime = picked;
      });
    }
  }

  void _showAddTaskDialog(BuildContext context) {
    final taskNameController = TextEditingController();

    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: const Text('Add New Task'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              TextField(
                controller: taskNameController,
                decoration: const InputDecoration(labelText: 'Task Name', border: OutlineInputBorder(borderRadius: BorderRadius.all(Radius.circular(20)))),
              ),
              const SizedBox(height: 16),
              Padding(
                padding: const EdgeInsets.all(20.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    ElevatedButton(
                      onPressed: () => _selectDate(context),
                      child: const Text('Date'),
                    ),
                    ElevatedButton(
                      onPressed: () => _selectTime(context),
                      child: const Text('Time'),
                    ),
                  ],
                ),
              ),
            ],
          ),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: const Icon (Icons.cancel_outlined),
            ),
            TextButton(
              onPressed: () {
                final name = taskNameController.text;
                final deadline = DateTime(
                  _selectedDate.year,
                  _selectedDate.month,
                  _selectedDate.day,
                  _selectedTime.hour,
                  _selectedTime.minute,
                );
                Provider.of<TaskProvider>(context, listen: false).addTask(name, deadline);
                Navigator.of(context).pop();
              },
              child: const Icon (Icons.add_circle_outline),
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        leading: const Icon (Icons.remove, color: Colors.transparent),
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
      floatingActionButton: FloatingActionButton(
        onPressed: () => _showAddTaskDialog(context),
        child: Icon(Icons.add),
      ),
    );
  }
}

class TasksContent extends StatelessWidget {
  const TasksContent({super.key});

  @override
  Widget build(BuildContext context) {
    return Consumer<TaskProvider>(
      builder: (context, taskProvider, child) {
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
                children: taskProvider.currentTasks
                    .map((task) => TaskItemWithTime(
                  taskName: task.name,
                  initialCompleted: task.completed,
                  onToggleCompletion: () => taskProvider.toggleTaskCompletion(task),
                  deadline: task.deadline,
                  onToggleDelete: () => taskProvider.deleteTask(task),
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
                children: taskProvider.doneTasks
                    .map((task) => TaskItemWithTime(
                  taskName: task.name,
                  initialCompleted: task.completed,
                  onToggleCompletion: () => taskProvider.toggleTaskCompletion(task),
                  deadline: task.deadline,
                  onToggleDelete: () => taskProvider.deleteTask(task),
                ))
                    .toList(),
              ),
            ],
          ),
        );
      },
    );
  }
}
