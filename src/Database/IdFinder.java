package Database;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import Exceptions.NotFoundException;
import Classes.*;

public class IdFinder {

    public static String[] findStudentByID(String id, File file) throws NotFoundException {

        String[] str = new String[8];

        //todo?
        List<String> listOfCourses = new ArrayList<>();
        Map<String, Double> listOfMarks = new HashMap<>();

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

                //todo?
                if (courses[1].length() > 2) {
                    String[] coursesIds = courses[1].substring(1, courses[1].length() - 1).split("~");
                    Collections.addAll(listOfCourses, coursesIds);
                }

                //todo?
                if (marks[1].length() > 2) {
                    String[] marksMap = marks[1].substring(1, marks[1].length() - 1).split("~");
                    for (String data : marksMap) {
                        listOfMarks.put(data.split("=")[0], Double.parseDouble(data.split("=")[1]));
                    }
                }

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

        String[] str = new String[4];

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

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                try {
                    IdFinder.findCourseByID(course[1], courseFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }
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

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                //todo?
                if (courses[1].length() > 2) {
                    String[] coursesIds = courses[1].substring(1, courses[1].length() - 1).split("~");
                    Collections.addAll(listOfCourses, coursesIds);
                }

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

        List<String> listOfStudents = new ArrayList<>();
        List<String> listOfAssignments = new ArrayList<>();
        Map<String, Double> marksList = new HashMap<>();

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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                if (!teacherId[1].equals("-"))
                    try {
                        IdFinder.findTeacherByID(teacherId[1], teacherFile);
                    } catch (NotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                if (students[1].length() > 2) {
                    String[] studentsIds = students[1].substring(1, students[1].length() - 1).split("~");
                    Collections.addAll(listOfStudents, studentsIds);
                }

                //todo?
//                if (students[1].length() > 2) {
//                    String[] studentsIds = students[1].substring(1, students[1].length() - 1).split("~");
//                    for (String stuId : studentsIds) {
//                        try {
//                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
//                            listOfStudents.add(tempStudent);
//                        } catch (NotFoundException e) {
//                            System.out.println(e.getMessage());
//                        }
//                    }
//                }

                //todo?
                String[] markDetails = marks[1].substring(1, marks[1].length() - 1).split("\\*");
                if (marks[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stuId, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                if (assignments[1].length() > 2) {
                    String[] assignmentsIds = assignments[1].substring(1, assignments[1].length() - 1).split("~");
                    Collections.addAll(listOfAssignments, assignmentsIds);
                }

                //todo?
//                if (assignments[1].length() > 2) {
//                    String[] assignmentsIds = assignments[1].substring(1, assignments[1].length() - 1).split("~");
//                    for (String assignId : assignmentsIds) {
//                        try {
//                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
//                            listOfAssignments.add(tempAssignment);
//                        } catch (NotFoundException e) {
//                            System.out.println(e.getMessage());
//                        }
//                    }
//                }

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
