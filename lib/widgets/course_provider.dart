import 'package:flutter/material.dart';
import 'package:flutter/foundation.dart';

class Course {
  String courseName;
  String teacherName;
  int numOfUnits;
  int numOfAssignments;
  String bestStudentName;

  Course({
    required this.courseName,
    required this.teacherName,
    required this.numOfUnits,
    required this.numOfAssignments,
    required this.bestStudentName,
  });
}

class CourseProvider with ChangeNotifier {
  List<Course> _courses = [];

  List<Course> get courses => _courses;

  void addCourse(String courseName, String teacherName, int numOfUnits,
      int numOfAssignments, String bestStudentName) {
    _courses.add(Course(courseName: courseName, teacherName: teacherName, numOfUnits: numOfUnits, numOfAssignments: numOfAssignments, bestStudentName: bestStudentName));
    notifyListeners();
  }

  void clearCourses() {
    _courses.clear();
    notifyListeners();
  }

  int getCourseCount() {
    return _courses.length;
  }

  void removeCourses(int count) {
    if (count <= 0) return;
    _courses = _courses.sublist(0, _courses.length - count);
    notifyListeners();
  }

}
