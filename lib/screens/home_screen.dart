import 'dart:io';

import 'package:flutter/material.dart';

import 'package:provider/provider.dart';

import 'package:radiohead/widgets/navigation_bar.dart';
import 'package:radiohead/widgets/summary_item.dart';
import 'package:radiohead/widgets/assignment_item.dart';
import 'package:radiohead/widgets/assignment_provider.dart';

import 'package:radiohead/screens/user_info_screen.dart';
import 'package:radiohead/screens/tasks_screen.dart';
import 'package:radiohead/screens/classes_screen.dart';
import 'package:radiohead/screens/news_screen.dart';
import 'package:radiohead/screens/assignments_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int _selectedIndex = 0;

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

    if (index == 2) {
      Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => const ClassesScreen())
      );
    }

    if (index == 3) {
      Navigator.push(context,
          MaterialPageRoute(builder: (context) => const NewsScreen()));
    }

    if (index == 4) {
      Navigator.push(context,
          MaterialPageRoute(builder: (context) => const AssignmentsScreen()));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.black54,
        toolbarHeight: 70,
        elevation: 0,
        leading: Padding(
          padding: const EdgeInsets.only(top: 10.0, left: 16.0),
          child: Align(
            alignment: Alignment.topLeft,
            child: IconButton(
              icon: const Icon(
                Icons.account_circle,
                size: 40.0,
                color: Colors.white,
              ),
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => const UserInfoScreen(),
                  ),
                );
              },
            ),
          ),
        ),
      ),
      extendBodyBehindAppBar: true,
      body: Stack(
        children: [
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

class HomeContent extends StatefulWidget {
  const HomeContent({super.key});

  @override
  HomeContentState createState() => HomeContentState();
}

class HomeContentState extends State<HomeContent> {
  String response = "";
  String? bestMark, worstMark, exams, homeworks, pastDeadline;
  String? assignmentName, assignmentDeadline;

  @override
  void initState() {
    super.initState();
    home("summary");
    home("home assignment");
  }
  
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Consumer<AssignmentProvider>(
        builder: (context, assignmentProvider, child) {
          return ListView(
            children: [
              const Text(
                'Summary',
                textAlign: TextAlign.left,
                style: TextStyle(
                    fontSize: 18, fontWeight: FontWeight.bold, color: Colors.white),
              ),
              const SizedBox(height: 16),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  SummaryItem(icon: Icons.star, text: 'Best mark: $bestMark'),
                  SummaryItem(icon: Icons.article, text: 'Exams: $exams'),
                  SummaryItem(icon: Icons.timer, text: 'Homeworks: $homeworks'),
                ],
              ),
              const SizedBox(height: 16),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  SummaryItem(icon: Icons.pending_actions, text: 'Past deadlines: $pastDeadline'),
                  SummaryItem(icon: Icons.sentiment_very_dissatisfied, text: 'Worst mark: $worstMark'),
                ],
              ),
              const SizedBox(height: 18),
              const Text(
                'To do',
                textAlign: TextAlign.left,
                style: TextStyle(
                    fontSize: 18, fontWeight: FontWeight.bold, color: Colors.white),
              ),
              const SizedBox(height: 12),
              Column(
                children: assignmentProvider.notFinishedAssignments
                    .map((assignment) => AssignmentItem(
                  assignmentName: assignment.name,
                  initialCompleted: assignment.completed,
                  onToggleCompletion: () => assignmentProvider.toggleAssignmentCompletion(assignment),
                  deadline: assignment.deadline,
                ))
                    .toList(),
              ),
              const SizedBox(height: 12),
              const Text(
                'Done!',
                textAlign: TextAlign.left,
                style: TextStyle(
                    fontSize: 18, fontWeight: FontWeight.bold, color: Colors.white),
              ),
              const SizedBox(height: 12),
              Column(
                children: assignmentProvider.finishedAssignments
                    .map((assignment) => AssignmentItem(
                  assignmentName: assignment.name,
                  initialCompleted: assignment.completed,
                  onToggleCompletion: () => assignmentProvider.toggleAssignmentCompletion(assignment),
                  deadline: assignment.deadline,
                ))
                    .toList(),
              ),
            ],
          );
        },
      ),
    );
  }

  Future<String> home(String command) async {
    try {
      final serverSocket = await Socket.connect("192.168.1.102", 2041);

      if (command == "summary") {
        serverSocket.write('summary\u0000');
        serverSocket.flush();

        serverSocket.listen((socketResponse) {
          setState(() {
            response = String.fromCharCodes(
                socketResponse); //bestMark~worstMark~exams~homeworks~pastDeadline
            bestMark = response.split("~")[0];
            worstMark = response.split("~")[1];
            exams = response.split("~")[2];
            homeworks = response.split("~")[3];
            pastDeadline = response.split("~")[4];
          });
        });
      } else if (command == "home assignment") {
        serverSocket.write('home assignment\u0000');
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
      }

      await Future.delayed(const Duration(seconds: 1));

      print("-----------server response is: { $response }");
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

class Task {
  String name;
  bool completed;

  Task({required this.name, this.completed = false});
}


