import 'Course.dart';

class Teacher {
  String teacherID;
  String firstName;
  String lastName;
  int? numberOfCourses;
  List<Course>? listOfCourses = [];

  Teacher(this.firstName, this.lastName, this.teacherID);
}