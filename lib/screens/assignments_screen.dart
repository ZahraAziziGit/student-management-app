import 'dart:io';

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

class AssignmentsScreen extends StatefulWidget {
  const AssignmentsScreen({super.key});

  @override
  State<AssignmentsScreen> createState() => _AssignmentsScreenState();
}

class _AssignmentsScreenState extends State<AssignmentsScreen> {
  int _selectedIndex = 4;
  DateTime _selectedDate = DateTime.now();

  final List<Widget> _widgetOptions = <Widget>[
    const HomeContent(),
    const TasksContent(),
    const ClassesContent(),
    const NewsContent(),
    const AssignmentsContent(),
  ];

  void _onItemTapped(int index) {
    if (index != _selectedIndex) {
      Provider.of<AssignmentProvider>(context, listen: false)
          .clearAssignments();
    }

    setState(() {
      _selectedIndex = index;
      Provider.of<AssignmentProvider>(context, listen: false)
          .filterAssignmentsByDate(_selectedDate);
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
      initialDate: _selectedDate,
      firstDate: DateTime(2024),
      lastDate: DateTime(2025),
    );
    if (picked != null && picked != _selectedDate) {
      setState(() {
        _selectedDate = picked;
        Provider.of<AssignmentProvider>(context, listen: false)
            .filterAssignmentsByDate(_selectedDate);
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
        actions: [
          IconButton(
            icon: const Padding(
              padding: EdgeInsets.all(18.0),
              child: Icon(Icons.calendar_today, color: Colors.white, size: 25),
            ),
            onPressed: () => _selectDate(context),
          ),
        ],
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

class AssignmentsContent extends StatefulWidget {
  const AssignmentsContent({super.key});

  @override
  AssignmentsContentState createState() => AssignmentsContentState();
}

class AssignmentsContentState extends State<AssignmentsContent> {
  String response = "";
  String? assignmentName, assignmentDeadline;

  @override
  void initState() {
    super.initState();
    _initializeAssignments();
    _removeHalfAssignments();
  }

  Future<void> _initializeAssignments() async {
    await assignment();
    _removeHalfAssignments();
  }

  void _removeHalfAssignments() {
    int halfCount = (Provider.of<AssignmentProvider>(context, listen: false).getAssignmentCount() / 3).floor();
    Provider.of<AssignmentProvider>(context, listen: false).removeAssignments(halfCount);
  }

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

  Future<String> assignment() async {
    try {
      final serverSocket = await Socket.connect("192.168.1.102", 2041);

      serverSocket.write('assignment\u0000');
      serverSocket.flush();

      serverSocket.listen((socketResponse) {
        setState(() {
          response = String.fromCharCodes(socketResponse);
          print("-----------server response is: { $response }");
          //assignmentName~assignmentDeadline#assignmentName~assignmentDeadline#
          List<String> data = response.split("#");
          for (int i = 0; i < data.length - 1; i++) {
            print("-----------details are: { ${data[i]} }");
            assignmentName = data[i].split("~")[0];
            assignmentDeadline = "${data[i].split("~")[1]} 23:59";
            Provider.of<AssignmentProvider>(context, listen: false)
                .addAssignment(
                assignmentName!, DateTime.parse(assignmentDeadline!));
          }
        });
      });

      await Future.delayed(const Duration(seconds: 1));
    } catch (e) {
      print("Error: $e");
      if (e is SocketException) {
        print(
            'SocketException: ${e.message}, address: ${e.address}, port: ${e.port}');
      }
    }
    return response;
  }
}

