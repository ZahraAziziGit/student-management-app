import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:radiohead/widgets/navigation_bar.dart';
import 'package:radiohead/widgets/assignment_provider.dart';
import 'package:radiohead/widgets/assignment_item.dart';
import 'package:radiohead/screens/home_screen.dart';
import 'package:radiohead/screens/tasks_screen.dart';
import 'package:radiohead/screens/classes_screen.dart';
import 'package:radiohead/screens/news_screen.dart';
import 'package:radiohead/widgets/assignment_details.dart';
import 'package:intl/intl.dart'; // Add this import

class AssignmentsScreen extends StatefulWidget {
  const AssignmentsScreen({super.key});

  @override
  State<AssignmentsScreen> createState() => _AssignmentsScreenState();
}

class _AssignmentsScreenState extends State<AssignmentsScreen> {
  int _selectedIndex = 4;
  DateTime _selectedDate = DateTime.now();
  TimeOfDay _selectedTime = TimeOfDay.now();

  final List<Widget> _widgetOptions = <Widget>[
    const HomeContent(),
    const TasksContent(),
    const ClassesContent(),
    const NewsContent(),
    const AssignmentsContent(),
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

    if (index == 2) {
      Navigator.push(context,
          MaterialPageRoute(builder: (context) => const ClassesScreen()));
    }

    if (index == 3) {
      Navigator.push(
          context, MaterialPageRoute(builder: (context) => const NewsScreen()));
    }

    if (index == 4) {
      Navigator.push(context,
          MaterialPageRoute(builder: (context) => const AssignmentsScreen()));
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

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        leading: const Icon(Icons.remove, color: Colors.transparent),
        toolbarHeight: 5,
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

class AssignmentsContent extends StatelessWidget {
  const AssignmentsContent({super.key});

  void _showAssignmentDetailsDialog(BuildContext context, String title,
      DateTime deadline, String estimatedTime, String description) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AssignmentDetailsDialog(
          title: title,
          deadline: deadline,
          estimatedTime: estimatedTime,
          description: description,
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Consumer<AssignmentProvider>(
      builder: (context, assignmentProvider, child) {
        return Padding(
          padding: const EdgeInsets.all(16.0),
          child: ListView(
            children: [
              const SizedBox(height: 18),
              const Text(
                'Not finished assignments',
                textAlign: TextAlign.left,
                style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: Colors.white),
              ),
              const SizedBox(height: 12),
              Column(
                children:
                    assignmentProvider.notFinishedAssignments.map((assignment) {
                  return GestureDetector(
                    onTap: () {
                      _showAssignmentDetailsDialog(
                        context,
                        assignment.name,
                        assignment.deadline,
                        '5',
                        'Description example',
                      );
                    },
                    child: AssignmentItem(
                      assignmentName: assignment.name,
                      initialCompleted: assignment.completed,
                      onToggleCompletion: () => assignmentProvider
                          .toggleAssignmentCompletion(assignment),
                      deadline: assignment.deadline,
                    ),
                  );
                }).toList(),
              ),
              const SizedBox(height: 12),
              const Text(
                'Finished assignments',
                textAlign: TextAlign.left,
                style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: Colors.white),
              ),
              const SizedBox(height: 12),
              Column(
                children:
                    assignmentProvider.finishedAssignments.map((assignment) {
                  return GestureDetector(
                    onTap: () {
                      _showAssignmentDetailsDialog(
                        context,
                        assignment.name,
                        assignment.deadline,
                        '5',
                        'Description example',
                      );
                    },
                    child: AssignmentItem(
                      assignmentName: assignment.name,
                      initialCompleted: assignment.completed,
                      onToggleCompletion: () => assignmentProvider
                          .toggleAssignmentCompletion(assignment),
                      deadline: assignment.deadline,
                    ),
                  );
                }).toList(),
              ),
            ],
          ),
        );
      },
    );
  }
}
