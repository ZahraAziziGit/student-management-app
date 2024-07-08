import 'package:flutter/material.dart';

import 'package:radiohead/widgets/navigation_bar.dart';
import 'package:radiohead/widgets/news_card.dart';

import 'package:radiohead/screens/home_screen.dart';
import 'package:radiohead/screens/tasks_screen.dart';
import 'package:radiohead/screens/classes_screen.dart';
import 'package:radiohead/screens/assignments_screen.dart';

class NewsScreen extends StatefulWidget {
  const NewsScreen({super.key});

  @override
  State<NewsScreen> createState() => _NewsScreenState();
}

class _NewsScreenState extends State<NewsScreen> {
  int _selectedIndex = 3;

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

class NewsContent extends StatefulWidget {
  const NewsContent({super.key});

  @override
  NewsContentState createState() => NewsContentState();
}

class NewsContentState extends State<NewsContent> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(20.0),
      child: Column(
        children: [
          const SizedBox(height: 25),
          const Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                "News",
                style: TextStyle(color: Colors.white, fontSize: 36),
              ),
            ],
          ),
          const SizedBox(height: 12),
          Expanded(
            child: SingleChildScrollView(
              child: Column(
                children: [
                  NewsCard(
                    newsTitle: "Introduction",
                    newsText:
                        "Welcome to the Faculty of Computer Science and Engineering, established in 1976...",
                    link: "Read more...",
                    uri: Uri.parse('https://encse.sbu.ac.ir/'),
                    image: "assets/images/sbu-logo.png",
                  ),
                  NewsCard(
                    newsTitle: "Faculty member of SBU wins IEEE award",
                    newsText: "Dr. Armin Salimi Badr, assistant professor...",
                    link: "Read more...",
                    uri: Uri.parse(
                        'https://encse.sbu.ac.ir/en/w/faculty-member-of-sbu-wins-ieee-education-and-research-award?redirect=%2F'),
                    image: "assets/images/sbu-logo.png",
                  ),
                  NewsCard(
                    newsTitle: "SBU ICPC team wins bronze medal",
                    newsText:
                        "The programming team of SBU won the bronze medal in ICPC 2023...",
                    link: "Read more...",
                    uri: Uri.parse(
                        'https://encse.sbu.ac.ir/en/w/icpc-programming-team-of-sbu-wins-bronze-medal-in-icpc-2023-regional-contest-2?redirect=%2F'),
                    image: "assets/images/sbu-logo.png",
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
