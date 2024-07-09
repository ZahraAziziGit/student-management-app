package Database;

import Classes.*;

import java.io.*;
import java.util.*;

public class StoreData {
    public static void storeStudent(String name, String lastName, String id, int numOfCourses,
                                    int numOfUnits, List<String> listOfCourse, double totalAvg,
                                    Map<String, Double> marks, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("name:" + name + ",lastname:" + lastName + ",id:" + id +
                    ",numOfCourses:" + numOfCourses + ",numOfUnits:" + numOfUnits +
                    ",courses:{");

            StringBuilder coursesId = new StringBuilder();
            for (String courseId : listOfCourse) {
                coursesId.append(courseId).append("~");
            }
            if (!coursesId.isEmpty()) {
                if (coursesId.charAt(0) == '~') {
                    writer.write(coursesId.substring(1, coursesId.length() - 1) + "},totalAvg:" + totalAvg + ",marks:{");
                } else {
                    writer.write(coursesId.substring(0, coursesId.length() - 1) + "},totalAvg:" + totalAvg + ",marks:{");
                }
            } else
                writer.write("},totalAvg:" + totalAvg + ",marks:{");

            StringBuilder marksStr = new StringBuilder();
            for (String courseId : marks.keySet()) {
                marksStr.append(courseId).append("=").append(marks.get(courseId)).append("~");
            }
            if (!marksStr.isEmpty()) {
                if (marksStr.charAt(0) == '~') {
                    writer.write(marksStr.substring(1, marksStr.length() - 1) + "}\n");
                } else {
                    writer.write(marksStr.substring(0, marksStr.length() - 1) + "}\n");

                }
            } else
                writer.write("}\n");

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeAssignment(String id, String deadline, boolean isActive, String courseId, String name, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("id:" + id + ",deadline:" + deadline + ",active:" + isActive + ",course:" + courseId + ",name:" + name + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeTeacher(String name, String lastName, String id, int numOfCourses,
                                    List<String> listOfCourse, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("name:" + name + ",lastname:" + lastName + ",id:" + id + ",numOfCourses:" + numOfCourses + ",courses:{");

            StringBuilder coursesId = new StringBuilder();
            for (String courseId : listOfCourse) {
                coursesId.append(courseId).append("~");
            }
            if (!coursesId.isEmpty()) {
                if (coursesId.charAt(0) == '~') {
                    writer.write(coursesId.substring(1, coursesId.length() - 1) + "}\n");
                } else {
                    writer.write(coursesId.substring(0, coursesId.length() - 1) + "}\n");
                }
            } else
                writer.write("}\n");

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeCourse(String name, String id,
                                   String teacherId, Map<String, Double> marks, int numOfUnits,
                                   int numOfStudents, List<String> listOfStudents, boolean isActive,
                                   int numOfAssignments, List<String> listOfAssignments,
                                   String examDate, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("name:" + name + ",id:" + id + ",teacherId:" + teacherId + ",marks:<");

            StringBuilder marksStr = new StringBuilder();
            for (String studentId : marks.keySet()) {
                marksStr.append(studentId).append("#").append(marks.get(studentId)).append("*");
            }
            if (!marksStr.isEmpty()) {
                if (marksStr.charAt(0) == '*') {
                    writer.write(marksStr.substring(1, marksStr.length() - 1) + ">,numOfUnits:" + numOfUnits +
                            ",numOfStudents:" + numOfStudents + ",students:{");
                } else {
                    writer.write(marksStr.substring(0, marksStr.length() - 1) + ">,numOfUnits:" + numOfUnits +
                            ",numOfStudents:" + numOfStudents + ",students:{");
                }
            } else
                writer.write(">,numOfUnits:" + numOfUnits + ",numOfStudents:" + numOfStudents + ",students:{");

            StringBuilder studentsId = new StringBuilder();
            for (String studentId : listOfStudents) {
                studentsId.append(studentId).append("~");
            }
            if (!studentsId.isEmpty()) {
                if (studentsId.charAt(0) == '~') {
                    writer.write(studentsId.substring(1, studentsId.length() - 1) + "},active:" + isActive +
                            ",numOfAssignments:" + numOfAssignments + ",assignments:{");
                } else {
                    writer.write(studentsId.substring(0, studentsId.length() - 1) + "},active:" + isActive +
                            ",numOfAssignments:" + numOfAssignments + ",assignments:{");
                }
            } else
                writer.write("},active:" + isActive + ",numOfAssignments:" + numOfAssignments + ",assignments:{");

            StringBuilder assignmentsId = new StringBuilder();
            for (String assignmentId : listOfAssignments) {
                assignmentsId.append(assignmentId).append("~");
            }
            if (!assignmentsId.isEmpty()) {
                if (assignmentsId.charAt(0) == '~') {
                    writer.write(assignmentsId.substring(1, assignmentsId.length() - 1) + "},examDate:" + examDate + "\n");
                }else {
                    writer.write(assignmentsId.substring(0, assignmentsId.length() - 1) + "},examDate:" + examDate + "\n");
                }            } else
                writer.write("},examDate:" + examDate + "\n");

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


