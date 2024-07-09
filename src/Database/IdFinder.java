package Database;

import java.io.*;
import java.util.*;

import Exceptions.NotFoundException;

public class IdFinder {

    public static String[] findStudentByID(String id, File file) throws NotFoundException {

        String[] str = new String[8];

        boolean doesStudentExist = false;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String[] studentData = reader.nextLine().split(",");

                String[] studentFirstName = studentData[0].split(":");
                str[0] = studentFirstName[1];

                String[] studentLastName = studentData[1].split(":");
                str[1] = studentLastName[1];

                String[] studentId = studentData[2].split(":");
                str[2] = studentId[1];

                String[] numOfCourses = studentData[3].split(":");
                str[3] = numOfCourses[1];

                String[] numOfUnits = studentData[4].split(":");
                str[4] = numOfUnits[1];

                String[] courses = studentData[5].split(":");
                str[5] = courses[1];

                String[] totalAvg = studentData[6].split(":");
                str[6] = totalAvg[1];

                String[] marks = studentData[7].split(":");
                str[7] = marks[1];

                if (Objects.equals(studentId[1], id)) {
                    doesStudentExist = true;
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (doesStudentExist)
            return str;
        throw new NotFoundException("Student", id);
    }

    public static String[] findAssignmentByID(String id, File file) throws NotFoundException {

        String[] str = new String[5];

        boolean doesAssignmentExist = false;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String[] assignmentData = reader.nextLine().split(",");

                String[] assignmentId = assignmentData[0].split(":");
                str[0] = assignmentId[1];

                String[] assignmentDeadline = assignmentData[1].split(":");
                str[1] = assignmentDeadline[1];

                String[] isActive = assignmentData[2].split(":");
                str[2] = isActive[1];

                String[] course = assignmentData[3].split(":");
                str[3] = course[1];

                String[] name = assignmentData[4].split(":");
                str[4] = name[1];

                if (Objects.equals(assignmentId[1], id)) {
                    doesAssignmentExist = true;
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (doesAssignmentExist)
            return str;
        throw new NotFoundException("Assignment", id);
    }

    public static String[] findTeacherByID(String id, File file) throws NotFoundException {

        String[] str = new String[5];

        List<String> listOfCourses = new ArrayList<>();

        boolean doesTeacherExist = false;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String[] teacherData = reader.nextLine().split(",");

                String[] teacherFirstName = teacherData[0].split(":");
                str[0] = teacherFirstName[1];

                String[] teacherLastName = teacherData[1].split(":");
                str[1] = teacherLastName[1];

                String[] teacherId = teacherData[2].split(":");
                str[2] = teacherId[1];

                String[] numOfCourses = teacherData[3].split(":");
                str[3] = numOfCourses[1];

                String[] courses = teacherData[4].split(":");
                str[4] = courses[1];

                if (Objects.equals(teacherId[1], id)) {
                    doesTeacherExist = true;
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (doesTeacherExist)
            return str;
        throw new NotFoundException("Teacher", id);
    }

    public static String[] findCourseByID(String id, File file) throws NotFoundException {

        String[] str = new String[11];

        boolean doesCourseExist = false;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");

                String[] courseName = courseData[0].split(":");
                str[0] = courseName[1];

                String[] courseId = courseData[1].split(":");
                str[1] = courseId[1];

                String[] teacherId = courseData[2].split(":");
                str[2] = teacherId[1];

                String[] marks = courseData[3].split(":");
                str[3] = marks[1];

                String[] units = courseData[4].split(":");
                str[4] = units[1];

                String[] numOfStudents = courseData[5].split(":");
                str[5] = numOfStudents[1];

                String[] students = courseData[6].split(":");
                str[6] = students[1];

                String[] isActive = courseData[7].split(":");
                str[7] = isActive[1];

                String[] numOfAssignments = courseData[8].split(":");
                str[8] = numOfAssignments[1];

                String[] assignments = courseData[9].split(":");
                str[9] = assignments[1];

                String[] examDate = courseData[10].split(":");
                str[10] = examDate[1];

                if (Objects.equals(courseId[1], id)) {
                    doesCourseExist = true;
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (doesCourseExist)
            return str;
        throw new NotFoundException("Course", id);
    }
}
