import 'package:flutter/material.dart';

import 'package:radiohead/widgets/class_card.dart';
import 'package:radiohead/widgets/navigation_bar.dart';

import 'package:radiohead/screens/tasks_screen.dart';
import 'package:radiohead/screens/home_screen.dart';

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

    if (index == 2) {
      Navigator.push(context,
          MaterialPageRoute(builder: (context) => const ClassesScreen()));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        leading: const Icon(Icons.remove, color: Colors.transparent),
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
    return Padding(
      padding: const EdgeInsets.all(20.0),
      child: Column(
        children: [
          const SizedBox(height: 20),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              const Text("Classes",
                  style: TextStyle(color: Colors.white, fontSize: 36)),
              IconButton(
                icon: const Icon(Icons.library_add,
                    color: Colors.white, size: 36),
                onPressed: () => _addNewClass(context),
              ),
            ],
          ),
          const SizedBox(height: 12),
          const Expanded(
            child: SingleChildScrollView(
              child: Column(
                children: [
                  ClassCard(
                    className: "Advanced Programming",
                    teacherName: "Dr. Vahidi",
                    numOfUnits: 3,
                    numOfAssignments: 4,
                    bestStudentName: "Taghi Taghavi",
                  ),
                  ClassCard(
                    className: "Advanced Programming",
                    teacherName: "Dr. Vahidi",
                    numOfUnits: 3,
                    numOfAssignments: 4,
                    bestStudentName: "Taghi Taghavi",
                  ),
                  ClassCard(
                    className: "Advanced Programming",
                    teacherName: "Dr. Vahidi",
                    numOfUnits: 3,
                    numOfAssignments: 4,
                    bestStudentName: "Taghi Taghavi",
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
