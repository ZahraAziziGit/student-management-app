import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;

import classes.*;
import database.*;

public class Main {

    //clear the commandline
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        //color codes
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";

        System.out.println("""
                -----------------Greetings!-----------------
                To access the system, please choose your role.
                If you are an Admin, type "Admin".
                If you are a Teacher, type "Teacher".""");
        System.out.print("Enter your role: ");

        //database directory
        String filePath = "./Database";
        File tempFile = new File(filePath);
        tempFile.mkdir();

        String studentPath = filePath + "/students.txt";
        String studentTempPath = filePath + "/temp_students.txt";
        String assignmentPath = filePath + "/assignments_data.txt";
        String assignmentTempPath = filePath + "/temp_assignments_data.txt";
        String teacherPath = filePath + "/teachers_data.txt";
        String teacherTempPath = filePath + "/temp_teachers_data.txt";
        String coursePath = filePath + "/courses_data.txt";
        String courseTempPath = filePath + "/temp_courses_data.txt";

        File studentFile = new File(studentPath);
        File studentTempFile = new File(studentTempPath);
        File assignmentFile = new File(assignmentPath);
        File assignmentTempFile = new File(assignmentTempPath);
        File teacherFile = new File(teacherPath);
        File teacherTempFile = new File(teacherTempPath);
        File courseFile = new File(coursePath);
        File courseTempFile = new File(courseTempPath);

        try {
            studentFile.createNewFile();
            assignmentFile.createNewFile();
            teacherFile.createNewFile();
            courseFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }

        String role;
        Teacher validatedTeacher = null;
        boolean isTeacherValid = false;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            role = scanner.nextLine();
            if ("Admin".equalsIgnoreCase(role)) {
                clear();
                System.out.println(GREEN + "Welcome Admin!" + "\n" + RESET
                        + "Choose from the list below:" + "\n" +
                        "---------------------------------");
                break;
            } else if ("Teacher".equalsIgnoreCase(role)) {
                clear();
                do {
                    System.out.print("Enter your ID: ");
                    String teacherID = scanner.nextLine();
                    try {
                        validatedTeacher = IdFinder.findTeacherByID(teacherID, teacherFile);
                        isTeacherValid = true;
                    } catch (NotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                } while (!isTeacherValid);
                System.out.println(GREEN + "Welcome Teacher!" + "\n" + RESET
                        + "Choose from the list below:" + "\n" +
                        "---------------------------------");
                break;
            } else {
                clear();
                System.out.println(RED + "Invalid role. Please try again." + RESET);
                System.out.print("Enter your role: ");
            }
        }

        if ("Admin".equalsIgnoreCase(role)) {
            boolean isExitEntered = false;
            while (!isExitEntered) {
                System.out.println("""
                        1. Add student
                        2. Remove student
                        3. Give mark to a student
                        4. Add assignment
                        5. Remove assignment
                        6. Change an assignment deadline
                        7. Add teacher
                        8. Remove teacher
                        9. Add course
                        10. Remove course
                        11. Exit
                        """);
                System.out.print("Enter a number: ");
                boolean isAdminActionChosen = false;
                while (!isAdminActionChosen) {
                    String adminAction = scanner.nextLine();

                    //variables
                    String studentFirstName, studentLastName, studentID,
                            assignmentID, assignmentDeadline = "",
                            teacherFirstName, teacherLastName, teacherID,
                            courseName, courseID, courseExamDate = "";
                    double mark = -1;
                    int numberOfUnits = 0;
                    LocalDate deadline = LocalDate.now();
                    Student student;
                    Teacher teacher;
                    Course course;
                    Assignment assignment;

                    switch (adminAction) {
                        case "1":
                            clear();
                            System.out.println(YELLOW + "Adding student..." + RESET);
                            System.out.print("Enter Student first name: ");
                            studentFirstName = scanner.nextLine();
                            System.out.print("Enter Student last name: ");
                            studentLastName = scanner.nextLine();
                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            student = new Student(studentFirstName, studentLastName, studentID);
                            StoreData.storeStudent(studentFirstName, studentLastName, studentID, studentFile);
                            course.addStudent(student);
                            System.out.println(GREEN + "Student \"" + student.getFirstName() + " "
                                    + student.getLastName() + "\" (ID: " + student.getStudentID() +
                                    ") has been successfully added to course \"" + course.getName() +
                                    "\" (ID: " + course.getCourseID() + ")." + RESET);
                            isAdminActionChosen = true;
                            break;
                        case "2":
                            clear();
                            System.out.println(YELLOW + "Removing student..." + RESET);
                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            try {
                                student = IdFinder.findStudentByID(studentID, studentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            UpdateData.updateStudentData(studentFile, studentTempFile, studentID);
                            course.removeStudent(student);
                            System.out.println(GREEN + "Student \"" + student.getFirstName() + " "
                                    + student.getLastName() + "\" (ID: " + student.getStudentID() +
                                    ") has been successfully removed from course \"" +
                                    course.getName() + "\" (ID: " + course.getCourseID() + ")." + RESET);
                            isAdminActionChosen = true;
                            break;
                        case "3":
                            clear();
                            System.out.println(YELLOW + "Giving mark to a student..." + RESET);
                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            try {
                                student = IdFinder.findStudentByID(studentID, studentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            boolean isMarkDouble = false;
                            do {
                                try {
                                    System.out.print("Enter mark: ");
                                    mark = scanner.nextDouble();
                                    isMarkDouble = true;
                                } catch (InputMismatchException e) {
                                    System.out.println(RED + "Mark should be decimal!" + RESET);
                                    scanner.nextLine(); //clear buffer
                                }
                            } while (!isMarkDouble);
                            student.marks.put(course, mark);
                            System.out.println(GREEN + "Student (ID: " + studentID + ") mark has been successfully added." + RESET);
                            isAdminActionChosen = true;
                            break;
                        case "4":
                            clear();
                            System.out.println(YELLOW + "Adding assignment..." + RESET);
                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();
                            boolean isDeadlineCorrect = false;
                            do {
                                try {
                                    System.out.print("Enter deadline (Format: yyyy-mm-dd): ");
                                    assignmentDeadline = scanner.nextLine();
                                    deadline = LocalDate.parse(assignmentDeadline);
                                    isDeadlineCorrect = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println(RED + "Deadline format is not correct!" + RESET);
                                }
                            } while (!isDeadlineCorrect);
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            assignment = new Assignment(assignmentID, deadline, true);
                            StoreData.storeAssignment(assignmentID, assignmentDeadline, true, assignmentFile);
                            course.getListOfAssignments().add(assignment);
                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") has been successfully added." + RESET);
                            isAdminActionChosen = true;
                            break;
                        case "5":
                            clear();
                            System.out.println(YELLOW + "Removing assignment..." + RESET);
                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            try {
                                assignment = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            UpdateData.updateAssignmentData(assignmentFile, assignmentTempFile, assignmentID);
                            course.getListOfAssignments().remove(assignment);
                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") has been successfully removed." + RESET);
                            isAdminActionChosen = true;
                            break;
                        case "6":
                            clear();
                            System.out.println(YELLOW + "Changing an assignment deadline..." + RESET);
                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();
                            boolean isNewDeadlineCorrect = false;
                            do {
                                try {
                                    System.out.print("Enter new deadline (Format: yyyy-mm-dd): ");
                                    assignmentDeadline = scanner.nextLine();
                                    deadline = LocalDate.parse(assignmentDeadline);
                                    isNewDeadlineCorrect = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println(RED + "Deadline format is not correct!" + RESET);
                                }
                            } while (!isNewDeadlineCorrect);
                            try {
                                assignment = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            UpdateData.updateAssignmentData(assignmentFile, assignmentTempFile, assignmentID, assignmentDeadline);
                            assignment.setDeadline(deadline);
                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") deadline has been successfully updated." + RESET);
                            isAdminActionChosen = true;
                            break;
                        case "7":
                            clear();
                            System.out.println(YELLOW + "Adding teacher..." + RESET);
                            System.out.print("Enter Teacher first name: ");
                            teacherFirstName = scanner.nextLine();
                            System.out.print("Enter Teacher last name: ");
                            teacherLastName = scanner.nextLine();
                            System.out.print("Enter Teacher ID: ");
                            teacherID = scanner.nextLine();
                            StoreData.storeTeacher(teacherFirstName, teacherLastName, teacherID, teacherFile);
                            System.out.println(GREEN + "Teacher \"" + teacherFirstName + " " + teacherLastName
                                    + "\" (ID: " + teacherID + ") has been successfully added" + RESET);
                            isAdminActionChosen = true;
                            break;
                        case "8":
                            clear();
                            System.out.println(YELLOW + "Removing teacher..." + RESET);
                            System.out.print("Enter Teacher ID: ");
                            teacherID = scanner.nextLine();
                            try {
                                IdFinder.findTeacherByID(teacherID, teacherFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            UpdateData.updateTeacherData(teacherFile, teacherTempFile, teacherID);
                            System.out.println(GREEN + "Teacher (ID: " + teacherID +
                                    ") has been successfully removed" + RESET);
                            isAdminActionChosen = true;
                            break;
                        case "9":
                            clear();
                            System.out.println(YELLOW + "Adding course..." + RESET);
                            System.out.print("Enter Course name: ");
                            courseName = scanner.nextLine();
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            System.out.print("Enter Teacher ID: ");
                            teacherID = scanner.nextLine();
                            try {
                                teacher = IdFinder.findTeacherByID(teacherID, teacherFile);
                            } catch (NotFoundException e){
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            boolean isUnitInt = false;
                            do {
                                try {
                                    System.out.print("Enter number of units: ");
                                    numberOfUnits = scanner.nextInt();
                                    isUnitInt = true;
                                } catch (InputMismatchException e) {
                                    System.out.println(RED + "Number of units must be an integer!" + RESET);
                                    scanner.nextLine(); //clear buffer
                                }
                            } while (!isUnitInt);
                            boolean isExamDateCorrect = false;
                            do {
                                try {
                                    System.out.print("Enter exam date (Format: yyyy-mm-dd): ");
                                    courseExamDate = scanner.nextLine();
                                    LocalDate.parse(courseExamDate);
                                    isExamDateCorrect = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println(RED + "Exam date format is not correct!" + RESET);
                                }
                            } while (!isExamDateCorrect);
                            StoreData.storeCourse(courseName, courseID, teacher.getFirstName(), teacher.getLastName(),
                                    teacher.getTeacherID(), String.valueOf(numberOfUnits), courseExamDate, true, courseFile);
                            System.out.println(GREEN + "Course \"" + courseName +
                                    "\" (ID: " + courseID + ") has been successfully added" + RESET);
                            isAdminActionChosen = true;
                            break;
                        case "10":
                            clear();
                            System.out.println(YELLOW + "Removing course..." + RESET);
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID);
                            System.out.println(GREEN + "Course (ID: " + courseID +
                                    ") has been successfully removed" + RESET);
                            isAdminActionChosen = true;
                            break;
                        case "11":
                            clear();
                            System.out.println(YELLOW + "Exiting..." + RESET);
                            isExitEntered = true;
                            isAdminActionChosen = true;
                            break;
                        default:
                            System.out.println(RED + "Invalid action, please choose again." + RESET);
                            System.out.print("Enter a number: ");
                            break;
                    }
                }
            }
        } else if ("Teacher".equalsIgnoreCase(role)) {
            boolean isExitEntered = false;
            while (!isExitEntered) {
                System.out.println("""
                        1. Add student
                        2. Remove student
                        3. Give mark to a student
                        4. Add assignment
                        5. Remove assignment
                        6. Change an assignment deadline
                        7. Add course
                        8. Remove course
                        9. Exit
                        """);
                System.out.print("Enter a number: ");
                boolean isTeacherActionChosen = false;
                while (!isTeacherActionChosen) {
                    String teacherAction = scanner.nextLine();

                    //variables
                    String studentID, courseID, assignmentID, assignmentDeadline;
                    double mark = -1;
                    LocalDate deadline = LocalDate.now();
                    Student student;
                    Course course;
                    Assignment assignment;

                    switch (teacherAction) {
                        case "1":
                            clear();
                            System.out.println(YELLOW + "Adding student..." + RESET);
                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();
                            try {
                                student = IdFinder.findStudentByID(studentID, studentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            validatedTeacher.addStudent(student, course);
                            System.out.println(GREEN + "Student \"" + student.getFirstName() + " "
                                    + student.getLastName() + "\" (ID: " + student.getStudentID() +
                                    ") has been successfully added to course \"" + course.getName() +
                                    "\" (ID: " + course.getCourseID() + ")." + RESET);
                            isTeacherActionChosen = true;
                            break;
                        case "2":
                            clear();
                            System.out.println(YELLOW + "Removing student..." + RESET);
                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();
                            try {
                                student = IdFinder.findStudentByID(studentID, studentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            validatedTeacher.removeStudent(student, course);
                            System.out.println(GREEN + "Student \"" + student.getFirstName() + " "
                                    + student.getLastName() + "\" (ID: " + student.getStudentID() +
                                    ") has been successfully removed from course \"" + course.getName() +
                                    "\" (ID: " + course.getCourseID() + ")." + RESET);
                            isTeacherActionChosen = true;
                            break;
                        case "3":
                            clear();
                            System.out.println(YELLOW + "Giving mark to a student..." + RESET);
                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();
                            try {
                                student = IdFinder.findStudentByID(studentID, studentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            boolean isMarkDouble = false;
                            do {
                                try {
                                    System.out.print("Enter mark: ");
                                    mark = scanner.nextDouble();
                                    isMarkDouble = true;
                                } catch (InputMismatchException e) {
                                    System.out.println(RED + "Mark should be decimal!" + RESET);
                                    scanner.nextLine(); //clear buffer
                                }
                            } while (!isMarkDouble);
                            validatedTeacher.giveMark(student, course, mark);
                            System.out.println(GREEN + "Student (ID: " + studentID + ") mark has been successfully added." + RESET);
                            isTeacherActionChosen = true;
                            break;
                        case "4":
                            clear();
                            System.out.println(YELLOW + "Adding assignment..." + RESET);
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();
                            boolean isDeadlineCorrect = false;
                            do {
                                try {
                                    System.out.print("Enter deadline (Format: yyyy-mm-dd): ");
                                    assignmentDeadline = scanner.nextLine();
                                    deadline = LocalDate.parse(assignmentDeadline);
                                    isDeadlineCorrect = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println(RED + "Deadline format is not correct!" + RESET);
                                }
                            } while (!isDeadlineCorrect);
                            assignment = new Assignment(assignmentID, deadline, true);
                            validatedTeacher.addAssignment(course, assignment);
                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") has been successfully added." + RESET);
                            isTeacherActionChosen = true;
                            break;
                        case "5":
                            clear();
                            System.out.println(YELLOW + "Removing assignment..." + RESET);
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();
                            try {
                                assignment = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            validatedTeacher.removeAssignment(course, assignment);
                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") has been successfully removed." + RESET);
                            isTeacherActionChosen = true;
                            break;
                        case "6":
                            clear();
                            System.out.println(YELLOW + "Changing deadline..." + RESET);
                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();
                            try {
                                assignment = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            boolean isNewDeadlineCorrect = false;
                            do {
                                try {
                                    System.out.print("Enter new deadline (Format: yyyy-mm-dd): ");
                                    assignmentDeadline = scanner.nextLine();
                                    deadline = LocalDate.parse(assignmentDeadline);
                                    isNewDeadlineCorrect = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println(RED + "Deadline format is not correct!" + RESET);
                                }
                            } while (!isNewDeadlineCorrect);
                            validatedTeacher.changeDeadline(assignment, deadline);
                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") deadline has been successfully updated." + RESET);
                            isTeacherActionChosen = true;
                            break;
                        case "7":
                            clear();
                            System.out.println(YELLOW + "Adding course..." + RESET);
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            validatedTeacher.addCourse(course);
                            System.out.println(GREEN + "Course \"" + course.getName() + "\" (ID: " + courseID +") has been successfully added" + RESET);
                            isTeacherActionChosen = true;
                            break;
                        case "8":
                            clear();
                            System.out.println(YELLOW + "Removing course..." + RESET);
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            validatedTeacher.removeCourse(course);
                            System.out.println(GREEN + "Course \"" + course.getName() + "\" (ID: " + courseID +") has been successfully removed" + RESET);
                            isTeacherActionChosen = true;
                            break;
                        case "9":
                            clear();
                            System.out.println(YELLOW + "Exiting..." + RESET);
                            isExitEntered = true;
                            isTeacherActionChosen = true;
                            break;
                        default:
                            System.out.println(RED + "Invalid action, please choose again." + RESET);
                            System.out.print("Enter a number: ");
                            break;
                    }
                }
            }
        }
    }
}