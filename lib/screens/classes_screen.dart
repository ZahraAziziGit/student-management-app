import 'dart:io';

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:radiohead/widgets/course_provider.dart';

import 'package:radiohead/widgets/navigation_bar.dart';
import 'package:radiohead/widgets/class_card.dart';

import 'package:radiohead/screens/home_screen.dart';
import 'package:radiohead/screens/tasks_screen.dart';
import 'package:radiohead/screens/news_screen.dart';
import 'package:radiohead/screens/assignments_screen.dart';

class ClassesScreen extends StatefulWidget {
  const ClassesScreen({super.key});

  @override
  State<ClassesScreen> createState() => _ClassesScreenState();
}

class _ClassesScreenState extends State<ClassesScreen> {
  int _selectedIndex = 2;

  final List<Widget> _widgetOptions = <Widget>[
    const HomeContent(),
    const TasksContent(),
    const ClassesContent(),
    const NewsContent(),
    const AssignmentsContent(),
  ];

  void _onItemTapped(int index) {
    if (index != _selectedIndex) {
      Provider.of<CourseProvider>(context, listen: false).clearCourses();
    }

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

  void _addNewClass(BuildContext context) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      builder: (BuildContext context) {
        return SingleChildScrollView(
          child: Padding(
            padding: EdgeInsets.only(
              bottom: MediaQuery.of(context).viewInsets.bottom,
            ),
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  const SizedBox(
                    height: 10,
                  ),
                  const Text(
                    'Add New Class',
                    style:
                        TextStyle(fontSize: 20, fontFamily: 'Times New Roman'),
                  ),
                  const SizedBox(height: 16),
                  TextField(
                    keyboardType: TextInputType.number,
                    decoration: InputDecoration(
                      labelText: 'Class Code',
                      labelStyle: const TextStyle(color: Colors.black54),
                      border: OutlineInputBorder(
                        borderSide: const BorderSide(
                          color: Colors.indigo,
                        ),
                        borderRadius: BorderRadius.circular(20),
                      ),
                    ),
                    onSubmitted: (String value) {
                      //todo
                      Navigator.pop(context);
                    },
                  ),
                  const SizedBox(height: 25),
                  ElevatedButton(
                    onPressed: () {
                      // todo
                      Navigator.pop(context);
                    },
                    child: const Text('Add Class'),
                  ),
                ],
              ),
            ),
          ),
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
        leading: const Icon(Icons.remove, color: Colors.transparent),
        actions: [
          IconButton(
            icon: const Padding(
              padding: EdgeInsets.all(18.0),
              child:
                  Icon(Icons.add_box_outlined, color: Colors.white, size: 30),
            ),
            onPressed: () => _addNewClass(context),
          ),
        ],
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

class ClassesContent extends StatefulWidget {
  const ClassesContent({super.key});

  @override
  ClassesContentState createState() => ClassesContentState();
}

class ClassesContentState extends State<ClassesContent> {
  String response = "";
  String? courseName, teacherName, units, assignments, topStudent;

  @override
  void initState() {
    super.initState();
    _initializeClasses();
  }

  Future<void> _initializeClasses() async {
    await classes();
    _removeHalfCourses();
  }

  void _removeHalfCourses() {
    int halfCount = (Provider.of<CourseProvider>(context, listen: false).getCourseCount() / 3).floor();
    Provider.of<CourseProvider>(context, listen: false).removeCourses(halfCount);
  }

  @override
  Widget build(BuildContext context) {
    return Consumer<CourseProvider>(
      builder: (context, courseProvider, child) {
        return Padding(
          padding: const EdgeInsets.all(16.0),
          child: ListView(
            children: [
              const SizedBox(height: 18),
              Column(
                children: courseProvider.courses.map((course) {
                  return SizedBox(
                    child: ClassCard(
                      className: course.courseName,
                      teacherName: course.teacherName,
                      numOfUnits: course.numOfUnits,
                      numOfAssignments: course.numOfAssignments,
                      bestStudentName: course.bestStudentName,
                    ),
                  );
                }).toList(),
              ),
              const SizedBox(
                height: 12,
              ),
            ],
          ),
        );
      },
    );
  }

  Future<String> classes() async {
    try {
      final serverSocket = await Socket.connect("192.168.1.102", 2041);

      serverSocket.write('classes\u0000');
      serverSocket.flush();

      serverSocket.listen((socketResponse) {
        setState(() {
          response = String.fromCharCodes(socketResponse);
          print("-----------server response is: { $response }");

          List<String> data = response.split("#");
          for (int i = 0; i < data.length - 1; i++) {
            print("-----------details are: { ${data[i]} }");
            courseName = data[i].split("~")[0];
            teacherName = data[i].split("~")[1];
            units = data[i].split("~")[2];
            assignments = data[i].split("~")[3];
            topStudent = data[i].split("~")[4];
            Provider.of<CourseProvider>(context, listen: false).addCourse(
              courseName!,
              teacherName!,
              int.parse(units!),
              int.parse(assignments!),
              topStudent!,
            );
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
