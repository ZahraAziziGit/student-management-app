package database;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import classes.*;

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
        String[] teacherFirstName = new String[2], teacherLastName = new String[2], teacherId = new String[2];
        boolean doesTeacherExist = false;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String[] teacherData = reader.nextLine().split(",");
                teacherFirstName = teacherData[0].split(":");
                teacherLastName = teacherData[1].split(":");
                teacherId = teacherData[2].split(":");
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
            return new Teacher(teacherFirstName[1], teacherLastName[1], teacherId[1]);
        throw new NotFoundException("Teacher", id);
    }

    public static Course findCourseByID(String id, File file) throws NotFoundException {
        String[] courseName = new String[2], courseId = new String[2], teacherData, teacherName = new String[2],
                teacherLastName = new String[2], teacherId = new String[2], units = new String[2],
                examDate = new String[2], isActive = new String[2];
        boolean doesCourseExist = false;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");
                courseName = courseData[0].split(":");
                courseId = courseData[1].split(":");
                teacherData = courseData[2].split(":");
                String[] teacherDetailedData = teacherData[1].split("~");
                teacherName = teacherDetailedData[0].split("=");
                teacherLastName = teacherDetailedData[1].split("=");
                teacherId = teacherDetailedData[2].split("=");
                units = courseData[3].split(":");
                examDate = courseData[4].split(":");
                isActive = courseData[5].split(":");
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
            return new Course(courseName[1], courseId[1], new Teacher(teacherName[1], teacherLastName[1], teacherId[1]),
                    Integer.parseInt(units[1]), LocalDate.parse(examDate[1]), Objects.equals(isActive[1], "true"));
        throw new NotFoundException("Course", id);
    }
}
