import 'Assignment.dart';
import 'Student.dart';
import 'Teacher.dart';

class Course {
  String name;
  String courseID;
  Teacher teacher;
  double highestMark = 0;
  double numberOfUnits;
  List<Student> listOfStudents = [];
  int numberOfStudents = 0;
  bool isCourseActive;
  List<Assignment> listOfAssignments = [];
  int? numberOfDefinedAssignments;
  int? numberOfActiveAssignments;
  DateTime dateOfExam;

  Course(
      this.name,
      this.courseID,
      this.teacher,
      this.numberOfUnits,
      this.dateOfExam,
      this.isCourseActive
      );
}