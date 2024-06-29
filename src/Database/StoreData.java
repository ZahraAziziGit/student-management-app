package Database;

import Classes.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StoreData {
    public static void storeStudent(String name, String lastName, String id, int numOfCourses,
                                    int numOfUnits, List<Course> listOfCourse, double totalAvg,
                                    Map<Course, Double> marks, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("name:" + name + ",lastname:" + lastName + ",id:" + id +
                    ",numOfCourses:" + numOfCourses + ",numOfUnits:" + numOfUnits +
                    ",courses:{");

            StringBuilder coursesId = new StringBuilder();
            for (Course course : listOfCourse) {
                coursesId.append(course.getCourseID()).append("~");
            }
            if (!coursesId.isEmpty())
                writer.write(coursesId.substring(0, coursesId.length() - 1) + "},totalAvg:" + totalAvg + ",marks:{");
            else
                writer.write("},totalAvg:" + totalAvg + ",marks:{");

            StringBuilder marksStr = new StringBuilder();
            for (Course course : marks.keySet()) {
                marksStr.append(course.getCourseID()).append("=").append(marks.get(course)).append("~");
            }
            if (!marksStr.isEmpty())
                writer.write(marksStr.substring(0, marksStr.length() - 1) + "}\n");
            else
                writer.write("}\n");

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeAssignment(String id, String deadline, boolean isActive, String courseId, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("id:" + id + ",deadline:" + deadline + ",active:" + isActive + ",course:" + courseId + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeTeacher(String name, String lastName, String id, int numOfCourses,
                                    List<Course> listOfCourse, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("name:" + name + ",lastname:" + lastName + ",id:" + id + ",numOfCourses:" + numOfCourses + ",courses:{");

            StringBuilder coursesId = new StringBuilder();
            for (Course course : listOfCourse) {
                coursesId.append(course.getCourseID()).append("~");
            }
            if (!coursesId.isEmpty())
                writer.write(coursesId.substring(0, coursesId.length() - 1) + "}\n");
            else
                writer.write("}\n");

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeCourse(String name, String id,
                                   String teacherName, String teacherLastName, String teacherId,
                                   int numOfCourses, List<Course> listOfCourse,
                                   Map<Student, Double> marks, int numOfUnits,
                                   int numOfStudents, List<Student> listOfStudents, boolean isActive,
                                   int numOfAssignments, List<Assignment> listOfAssignments,
                                   String examDate,  File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("name:" + name + ",id:" + id + ",teacher:[name=" + teacherName + "$lastname=" + teacherLastName
                    + "$id=" + teacherId + "$numOfCourses=" + numOfCourses + "$courses={");

            StringBuilder coursesId = new StringBuilder();
            for (Course course : listOfCourse) {
                coursesId.append(course.getCourseID()).append("~");
            }
            if (!coursesId.isEmpty())
                writer.write(coursesId.substring(0, coursesId.length() - 1) + "}],marks:<");
            else
                writer.write("}],marks:<");

            StringBuilder marksStr = new StringBuilder();
            for (Student student : marks.keySet()) {
                marksStr.append(student.getStudentID()).append("#").append(marks.get(student)).append("*");
            }
            if (!marksStr.isEmpty())
                writer.write(marksStr.substring(0, marksStr.length() - 1) + ">,numOfUnits:" + numOfUnits +
                         ",numOfStudents" + numOfStudents + ",students:{");
            else
                writer.write(">,numOfUnits:" + numOfUnits + ",numOfStudents" + numOfStudents + ",students:{");

            StringBuilder studentsId = new StringBuilder();
            for (Student student : listOfStudents) {
                studentsId.append(student.getStudentID()).append("~");
            }
            if (!coursesId.isEmpty())
                writer.write(studentsId.substring(0, studentsId.length() - 1) + "},active:" + isActive +
                        ",numOfAssignments:" + numOfAssignments + ",assignments:{");
            else
                writer.write("},active:" + isActive + ",numOfAssignments:" + numOfAssignments + ",assignments:{");

            StringBuilder assignmentsId = new StringBuilder();
            for (Assignment assignment : listOfAssignments) {
                assignmentsId.append(assignment.getAssignmentID()).append("~");
            }
            if (!coursesId.isEmpty())
                writer.write(assignmentsId.substring(0, assignmentsId.length() - 1) + "},examData:" + examDate + "\n");
            else
                writer.write("},examData:" + examDate + "\n");

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


