package Database;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import Exceptions.NotFoundException;
import Classes.*;

public class IdFinder {

    public static Student findStudentByID(String id, File file) throws NotFoundException {

        String[] studentFirstName = new String[2], studentLastName = new String[2], studentId = new String[2],
                numOfCourses = new String[2], numOfUnits = new String[2], totalAvg = new String[2];
        List<Course> listOfCourses = new ArrayList<>();
        Map<Course, Double> listOfMarks = new HashMap<>();

        boolean doesStudentExist = false;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String[] studentData = reader.nextLine().split(",");
                studentFirstName = studentData[0].split(":");
                studentLastName = studentData[1].split(":");
                studentId = studentData[2].split(":");
                numOfCourses = studentData[3].split(":");
                numOfUnits = studentData[4].split(":");
                String[] courses = studentData[5].split(":");
                totalAvg = studentData[6].split(":");
                String[] marks = studentData[7].split(":");

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                if (courses[1].length() > 2) {
                    String[] coursesIds = courses[1].substring(1, courses[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCourses.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                if (marks[1].length() > 2) {
                    String[] marksMap = marks[1].substring(1, marks[1].length() - 1).split("~");
                    for (String data : marksMap) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(data.split("=")[0], courseFile);
                            listOfMarks.put(tempCourse, Double.parseDouble(data.split("=")[1]));
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
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
            return new Student(studentFirstName[1], studentLastName[1], studentId[1], Integer.parseInt(numOfCourses[1]),
                    Integer.parseInt(numOfUnits[1]), listOfCourses, Double.parseDouble(totalAvg[1]), listOfMarks);
        throw new NotFoundException("Student", id);
    }

    public static Assignment findAssignmentByID(String id, File file) throws NotFoundException {

        String[] assignmentId = new String[2], assignmentDeadline = new String[2], isActive = new String[2];

        boolean doesAssignmentExist = false;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String[] assignmentData = reader.nextLine().split(",");
                assignmentId = assignmentData[0].split(":");
                assignmentDeadline = assignmentData[1].split(":");
                isActive = assignmentData[2].split(":");
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
            return new Assignment(assignmentId[1], LocalDate.parse(assignmentDeadline[1]), Objects.equals(isActive[1], "true"));
        throw new NotFoundException("Assignment", id);
    }

    public static Teacher findTeacherByID(String id, File file) throws NotFoundException {

        String[] teacherFirstName = new String[2], teacherLastName = new String[2], teacherId = new String[2],
                numOfCourses = new String[2];
        List<Course> listOfCourses = new ArrayList<>();

        boolean doesTeacherExist = false;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String[] teacherData = reader.nextLine().split(",");
                teacherFirstName = teacherData[0].split(":");
                teacherLastName = teacherData[1].split(":");
                teacherId = teacherData[2].split(":");
                numOfCourses = teacherData[3].split(":");
                String[] courses = teacherData[4].split(":");

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                if (courses[1].length() > 2) {
                    String[] coursesIds = courses[1].substring(1, courses[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCourses.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
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
            return new Teacher(teacherFirstName[1], teacherLastName[1], teacherId[1], Integer.parseInt(numOfCourses[1]),
                    listOfCourses);
        throw new NotFoundException("Teacher", id);
    }

    public static Course findCourseByID(String id, File file) throws NotFoundException {

        String[] courseName = new String[2], courseId = new String[2], teacherData, teacherName = new String[2],
                teacherLastName = new String[2], teacherId = new String[2],marks ,numOfCourses = new String[2],
                units = new String[2], numOfStudents = new String[2], isActive = new String[2],
                numOfAssignments = new String[2], examDate = new String[2];

        List<Course> listOfCourses = new ArrayList<>();
        List<Student> listOfStudents = new ArrayList<>();
        List<Assignment> listOfAssignments = new ArrayList<>();
        Map<Student, Double> marksList = new HashMap<>();

        boolean doesCourseExist = false;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");
                courseName = courseData[0].split(":");
                courseId = courseData[1].split(":");

                teacherData = courseData[2].split(":");
                String[] teacherDetailedData = teacherData[1].substring(1, teacherData[1].length() - 1).split("\\$");
                teacherName = teacherDetailedData[0].split("=");
                teacherLastName = teacherDetailedData[1].split("=");
                teacherId = teacherDetailedData[2].split("=");
                numOfCourses = teacherDetailedData[3].split("=");
                String[] courses = teacherDetailedData[4].split("=");

                marks = courseData[3].split(":");
                String[] markDetails = marks[1].substring(1, marks[1].length() - 1).split("\\*");
                units = courseData[4].split(":");
                numOfStudents = courseData[5].split(":");
                String[] students = courseData[6].split(":");
                isActive = courseData[7].split(":");
                numOfAssignments = courseData[8].split(":");
                String[] assignments = courseData[9].split(":");
                examDate = courseData[10].split(":");

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                if (courses[1].length() > 2) {
                    String[] coursesIds = courses[1].substring(1, courses[1].length() - 1).split("~");
                    for (String couId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(couId, courseFile);
                            listOfCourses.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                if (students[1].length() > 2) {
                    String[] studentsIds = students[1].substring(1, courses[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                if (marks[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
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
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

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
            return new Course(courseName[1], courseId[1], new Teacher(teacherName[1], teacherLastName[1], teacherId[1],
                    Integer.parseInt(numOfCourses[1]), listOfCourses), marksList, Integer.parseInt(units[1]),
                    listOfStudents, Integer.parseInt(numOfStudents[1]), Objects.equals(isActive[1], "true"),
                    listOfAssignments, Integer.parseInt(numOfAssignments[1]), LocalDate.parse(examDate[1]));
        throw new NotFoundException("Course", id);
    }
}
