import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import Exceptions.*;
import Classes.*;
import Database.*;

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
        //String filePath = ".\\Database"; //for windows, uncomment this and comment the previous line.
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

        //for windows, uncomment this part and comment the previous part.
    /*  String studentPath = filePath + "\\students.txt";
        String studentTempPath = filePath + "\\temp_students.txt";
        String assignmentPath = filePath + "\\assignments_data.txt";
        String assignmentTempPath = filePath + "\\temp_assignments_data.txt";
        String teacherPath = filePath + "\\teachers_data.txt";
        String teacherTempPath = filePath + "\\temp_teachers_data.txt";
        String coursePath = filePath + "\\courses_data.txt";
        String courseTempPath = filePath + "\\temp_courses_data.txt"; */

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
            System.out.println(e.getMessage());
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
                        1. Add a new student
                        2. Remove a student
                        3. Give mark to a student
                        4. Add a new assignment
                        5. Remove an assignment
                        6. Change an assignment deadline
                        7. Change an assignment status
                        8. Add a new teacher
                        9. Remove a teacher
                        10. Add a new course
                        11. Remove a course
                        12. Add a teacher to a course
                        13. Remove a teacher from a course
                        14. Add an assignment to a course
                        15. Remove an assignment from a course
                        16. Add a student to a course
                        17. Remove a student from a course
                        18. Change a course status
                        19. Change a course exam date
                        20. Exit
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

                            StoreData.storeStudent(studentFirstName, studentLastName, studentID,
                                    0, 0,
                                    new ArrayList<>(), 0.0, new HashMap<>(), studentFile);

                            System.out.println(GREEN + "Student \"" + studentFirstName + " "
                                    + studentLastName + "\" (ID: " + studentID +
                                    ") has been successfully added." + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "2":
                            clear();
                            System.out.println(YELLOW + "Removing student..." + RESET);

                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();


                            try {
                                student = IdFinder.findStudentByID(studentID, studentFile);
                                List<Course> studentCourses = student.getListOfCourses();
                                for (Course cou : studentCourses) {
                                    Course tempCourse = IdFinder.findCourseByID(cou.getCourseID(), courseFile);
                                    tempCourse.removeStudent(student);
                                    UpdateData.updateCourseData(courseFile, courseTempFile, tempCourse.getCourseID(),
                                            tempCourse.getListOfStudents(), tempCourse.getNumberOfStudents());
                                }
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }

                            UpdateData.updateStudentData(studentFile, studentTempFile, studentID);

                            System.out.println(GREEN + "Student \"" + student.getFirstName() + " "
                                    + student.getLastName() + "\" (ID: " + student.getStudentID() +
                                    ") has been successfully removed." + RESET);
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
                                student = IdFinder.findStudentByID(studentID, studentFile);
                                course = IdFinder.findCourseByID(courseID, courseFile);
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

                            try {
                                student.addMark(course, mark);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            UpdateData.updateCourseData(courseFile, courseTempFile, course.getCourseID(), course.getMarks());
                            UpdateData.updateStudentData(studentFile, studentTempFile, student.getStudentID(),
                                    student.getTotalAverage(), student.getMarks());

                            System.out.println(GREEN + "Student (ID: " + studentID + ") mark (" + mark + " for \""
                                    + course.getName() + "\") has been successfully added." + RESET);
                            scanner.nextLine(); //clear buffer
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

                            StoreData.storeAssignment(assignmentID, assignmentDeadline, true, "", assignmentFile);

                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") has been successfully added." + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "5":
                            clear();
                            System.out.println(YELLOW + "Removing assignment..." + RESET);

                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();

                            try {
                                assignment = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
                                Course tempCourse = IdFinder.findCourseByID(assignment.getCourseID(), courseFile);
                                tempCourse.removeAssignment(assignment);
                                UpdateData.updateCourseData(courseFile, courseTempFile, tempCourse.getCourseID(),
                                        tempCourse.getNumberOfAssignments(), tempCourse.getListOfAssignments());
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            UpdateData.updateAssignmentData(assignmentFile, assignmentTempFile, assignmentID);

                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") has been successfully removed." + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "6":
                            clear();
                            System.out.println(YELLOW + "Changing an assignment deadline..." + RESET);

                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();

                            try {
                                assignment = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
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

                            assignment.setDeadline(deadline);
                            UpdateData.updateAssignmentData(assignmentFile, assignmentTempFile, assignmentID, assignmentDeadline);


                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") deadline has been successfully updated." + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "7":
                            clear();
                            System.out.println(YELLOW + "Changing an assignment status..." + RESET);

                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();

                            try {
                                assignment = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }

                            assignment.setActive(!assignment.isActive());
                            UpdateData.updateAssignmentData(assignmentFile, assignmentTempFile, assignmentID, assignment.isActive());

                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") status has been successfully" +
                                    " changed from " + (!assignment.isActive() ? "active" : "not active") + " to " +
                                    (assignment.isActive() ? "active." : "not active.") + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "8":
                            clear();
                            System.out.println(YELLOW + "Adding teacher..." + RESET);

                            System.out.print("Enter Teacher first name: ");
                            teacherFirstName = scanner.nextLine();
                            System.out.print("Enter Teacher last name: ");
                            teacherLastName = scanner.nextLine();
                            System.out.print("Enter Teacher ID: ");
                            teacherID = scanner.nextLine();

                            StoreData.storeTeacher(teacherFirstName, teacherLastName, teacherID, 0,
                                    new ArrayList<>(), teacherFile);

                            System.out.println(GREEN + "Teacher \"" + teacherFirstName + " " + teacherLastName
                                    + "\" (ID: " + teacherID + ") has been successfully added" + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "9":
                            clear();
                            System.out.println(YELLOW + "Removing teacher..." + RESET);

                            System.out.print("Enter Teacher ID: ");
                            teacherID = scanner.nextLine();
                            try {
                                teacher = IdFinder.findTeacherByID(teacherID, teacherFile);
                                List<Course> teacherCourses = teacher.getListOfCourses();
                                for (Course cou : teacherCourses) {
                                    Course tempCourse = IdFinder.findCourseByID(cou.getCourseID(), courseFile);
                                    tempCourse.setTeacher(null);
                                    UpdateData.updateCourseData(courseFile, courseTempFile, tempCourse.getCourseID(), tempCourse.getTeacher());
                                }
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

                        case "10":
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
                            } catch (NotFoundException e) {
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
                                    teacher.getTeacherID(), teacher.getNumberOfCourses(), teacher.getListOfCourses(),
                                    new HashMap<>(), numberOfUnits, 0, new ArrayList<>(), true,
                                    0, new ArrayList<>(), courseExamDate, courseFile);

                            System.out.println(GREEN + "Course \"" + courseName +
                                    "\" (ID: " + courseID + ") has been successfully added" + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "11":
                            clear();
                            System.out.println(YELLOW + "Removing course..." + RESET);

                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                course = IdFinder.findCourseByID(courseID, courseFile);

                                teacher = course.getTeacher();
                                teacher.removeCourse(course);
                                UpdateData.updateTeacherData(teacherFile, teacherTempFile, teacher.getTeacherID(),
                                        teacher.getNumberOfCourses(), teacher.getListOfCourses());

                                List<Student> courseStudents = course.getListOfStudents();
                                for (Student stu : courseStudents) {
                                    Student tempStudent = IdFinder.findStudentByID(stu.getStudentID(), studentFile);
                                    tempStudent.removeCourse(course);
                                    UpdateData.updateStudentData(studentFile, studentTempFile, tempStudent.getStudentID(),
                                            tempStudent.getNumberOfCourses(), tempStudent.getNumberOfUnits(), tempStudent.getListOfCourses());
                                }

                                List<Assignment> courseAssignments = course.getListOfAssignments();
                                for (Assignment assign : courseAssignments) {
                                    Assignment tempAssignment = IdFinder.findAssignmentByID(assign.getAssignmentID(), assignmentFile);
                                    tempAssignment.setCourseID("");
                                    UpdateData.updateAssignmentCourse(assignmentFile, assignmentTempFile,
                                            tempAssignment.getAssignmentID(), tempAssignment.getCourseID());
                                }
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

                        case "12":
                            clear();
                            System.out.println(YELLOW + "Adding teacher to a course..." + RESET);
                            //todo

                        case "13":
                            clear();
                            System.out.println(YELLOW + "Removing teacher from a course..." + RESET);
                            //todo

                        case "14":
                            clear();
                            System.out.println(YELLOW + "Adding assignment to a course..." + RESET);
                            //todo

                        case "15":
                            clear();
                            System.out.println(YELLOW + "Removing assignment from a course..." + RESET);
                            //todo

                        case "16":
                            clear();
                            System.out.println(YELLOW + "Adding student to a course..." + RESET);
                            //todo

                        case "17":
                            clear();
                            System.out.println(YELLOW + "Removing student from a course..." + RESET);
                            //todo

                        case "18":
                            clear();
                            System.out.println(YELLOW + "Changing course status..." + RESET);
                            //todo

                        case "19":
                            clear();
                            System.out.println(YELLOW + "Changing course exam date..." + RESET);
                            //todo


                        case "20":
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
                        1. Add a student to a course
                        2. Remove a student from a course
                        3. Give mark to a student
                        4. Add a new assignment and add it to a course
                        5. Remove an assignment
                        6. Change an assignment deadline
                        7. Exit
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
                            try {
                                validatedTeacher.addStudent(student, course);
                            } catch (AlreadyExistsException e) {
                                System.out.println(e.getMessage());
                            }
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
                            try {
                                validatedTeacher.removeStudent(student, course);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
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

                            try {
                                validatedTeacher.giveMark(student, course, mark);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }

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
                            assignment = new Assignment(assignmentID, deadline, true, "");
                            try {
                                validatedTeacher.addAssignment(course, assignment);
                            } catch (AlreadyExistsException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
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

                            try {
                                validatedTeacher.removeAssignment(course, assignment);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }

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
                            try {
                                validatedTeacher.addCourse(course);
                            } catch (AlreadyExistsException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            System.out.println(GREEN + "Course \"" + course.getName() + "\" (ID: " + courseID + ") has been successfully added" + RESET);
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
                            try {
                                validatedTeacher.removeCourse(course);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }
                            System.out.println(GREEN + "Course \"" + course.getName() + "\" (ID: " + courseID + ") has been successfully removed" + RESET);
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