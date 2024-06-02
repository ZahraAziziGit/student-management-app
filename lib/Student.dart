import 'Course.dart';

class Student {
  String firstName;
  String lastName;
  String studentID;
  String? password;
  int? numberOfCourses;
  int? numberOfUnits;
  List<Course>? listOfCourses = [];
  double? totalAverage;
  double? currentTermAverage;
  Map<double, double>? marks = {};

  Student(this.firstName, this.lastName, this.studentID);
}