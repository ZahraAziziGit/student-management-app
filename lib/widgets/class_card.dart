import 'package:flutter/material.dart';

class ClassCard extends StatelessWidget {
  final String className;
  final String teacherName;
  final int numOfUnits;
  final int numOfAssignments;
  final String bestStudentName;

  const ClassCard({
    super.key,
    required this.className,
    required this.teacherName,
    required this.numOfUnits,
    required this.numOfAssignments,
    required this.bestStudentName,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.only(top: 10),
      width: MediaQuery.of(context).size.width,
      height: 200,
      decoration: BoxDecoration(
        color: Colors.indigo,
        borderRadius: BorderRadius.circular(20.0),
      ),
      child: Column(
        children: [
          const SizedBox(
            height: 10,
          ),
          Padding(
            padding: const EdgeInsets.only(left: 18.0),
            child: SizedBox(
                height: 30,
                child: Row(
                  children: [
                    const Icon(Icons.school, color: Colors.white),
                    const SizedBox(
                      width: 10,
                    ),
                    Text(className,
                        style: const TextStyle(
                            fontSize: 20,
                            color: Colors.white,
                            fontFamily: 'Georgia')),
                  ],
                )),
          ),
          Padding(
            padding: const EdgeInsets.only(left: 20),
            child: Align(
              alignment: Alignment.centerLeft,
              child: Text("Teacher: $teacherName",
                  style: const TextStyle(
                      fontSize: 14, color: Colors.white54, fontFamily: 'Georgia')),
            ),
          ),
          const SizedBox(
            height: 6,
          ),
          const Divider(
            endIndent: 20,
            indent: 20,
          ),
          Padding(
            padding: const EdgeInsets.only(left: 18.0, bottom: 18),
            child: Row(
              children: [
                const Icon(
                  Icons.pin,
                  color: Colors.white,
                  size: 18,
                ),
                const SizedBox(
                  width: 10,
                ),
                Text("Units: $numOfUnits",
                    style: const TextStyle(
                        fontSize: 14,
                        color: Colors.white,
                        fontFamily: 'Georgia')),
              ],
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(left: 18.0, bottom: 18),
            child: Row(
              children: [
                const Icon(
                  Icons.assignment,
                  color: Colors.white,
                  size: 18,
                ),
                const SizedBox(
                  width: 10,
                ),
                Text("Assignments: $numOfAssignments",
                    style: const TextStyle(
                        fontSize: 14,
                        color: Colors.white,
                        fontFamily: 'Georgia')),
              ],
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(left: 18.0, bottom: 18),
            child: Row(
              children: [
                const Icon(
                  Icons.emoji_events,
                  color: Colors.white,
                  size: 18,
                ),
                const SizedBox(
                  width: 10,
                ),
                Text("Top Student: $bestStudentName",
                    style: const TextStyle(
                        fontSize: 14,
                        color: Colors.white,
                        fontFamily: 'Georgia')),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
