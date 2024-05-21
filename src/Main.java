import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    //method to clear the commandline
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

        //first message
        System.out.println("""
                -----------------Greetings!-----------------
                To access the system, please choose your role.
                If you are an Admin, type "Admin".
                If you are a Teacher, type "Teacher".""");
        System.out.print("Enter your role: ");

        Scanner scanner = new Scanner(System.in);
        String currentRole;

        //choosing role
        while (true) {
            String role = scanner.nextLine();
            if ("Admin".equalsIgnoreCase(role)) {
                clear();
                System.out.println(GREEN + "Welcome Admin!" + "\n" + RESET
                        + "Choose from the list below:" + "\n" +
                        "---------------------------------");
                currentRole = role;
                break;
            } else if ("Teacher".equalsIgnoreCase(role)) {
                clear();
                System.out.print("Enter your teacher's ID: ");
                String teacherID = scanner.nextLine();
                //TODO: validate teacher's id
                System.out.println(GREEN + "Welcome Teacher!" + "\n" + RESET
                        + "Choose from the list below:" + "\n" +
                        "---------------------------------");
                currentRole = role;
                break;
            } else {
                clear();
                System.out.println(RED + "Invalid Role, Please Try Again." + RESET);
                System.out.print("Enter your role: ");
            }
        }

        //Admin menu
        if ("Admin".equalsIgnoreCase(currentRole)) {
            System.out.println("""
                    1. Add student
                    2. Remove student
                    3. Give mark to a student
                    4. Change an assignment deadline
                    5. Add assignment
                    6. Remove assignment
                    7. Add teacher
                    8. Remove teacher
                    9. Add course
                    10. Remove course
                    """);
            System.out.print("Enter a number: ");
        }

        boolean isAdminActionChosen = false;
        while (!isAdminActionChosen) {
            String adminAction = scanner.nextLine();
            //----------------------------------------
            /*these are temp objects and are going to be
            replaced when backend is finished*/
            Student student = new Student("name", "family", "1");
            Teacher teacher = new Teacher("12", "name", "family");
            Course course = new Course("name", "12", teacher, 2,
                    LocalDate.of(2020, 12, 12));
            Assignment assignment = new Assignment(LocalDate.of(2020, 12, 12),
                    true);
            //----------------------------------------

            //variables
            String studentID, courseID, assignmentID, assignmentDeadline, teacherID,
                    teacherFirstName, teacherLastName, courseName, courseExamDate;
            double mark = -1;
            int numberOfUnits = 0;
            LocalDate deadline = LocalDate.now(), dateOfExam = LocalDate.now();

            switch (adminAction) {
                case "1": //add student
                    clear();
                    System.out.println(YELLOW + "Adding student..." + RESET);
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Course ID: ");
                    courseID = scanner.nextLine();
                    //TODO: find student and course with this ID
                    //temp actions-----------------
                    course.addStudent(student);
                    //-----------------------------
                    System.out.println(GREEN + "Student \"" + student.getFirstName() + " " + student.getLastName() +
                            "\" with ID: " + student.getStudentID() + " has been successfully added to course \"" +
                            course.getName() + "\" with ID: " + course.getCourseID() + "." + RESET);
                    isAdminActionChosen = true;
                    break;
                case "2": //remove student
                    clear();
                    System.out.println(YELLOW + "Removing student..." + RESET);
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Course ID: ");
                    courseID = scanner.nextLine();
                    //TODO: find student and course with this ID
                    //temp actions-----------------
                    course.removeStudent(student);
                    //-----------------------------
                    System.out.println(GREEN + "Student \"" + student.getFirstName() + " " + student.getLastName() +
                            "\" with ID: " + student.getStudentID() + " has been successfully removed from course " +
                            course.getName() + "\" with ID: " + course.getCourseID() + "." + RESET);
                    isAdminActionChosen = true;
                    break;
                case "3": //give mark
                    clear();
                    System.out.println(YELLOW + "Giving mark to a student..." + RESET);
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Course ID: ");
                    courseID = scanner.nextLine();
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
                    //TODO: find student and course with this ID
                    //temp actions-----------------
                    student.marks.put(mark, course.getNumberOfUnits());
                    //-----------------------------
                    System.out.println(GREEN + "Student mark has been successfully added." + RESET);
                    isAdminActionChosen = true;
                    break;
                case "4": //change deadline
                    clear();
                    System.out.println(YELLOW + "Changing deadline..." + RESET);
                    System.out.print("Enter Assignment ID: ");
                    assignmentID = scanner.nextLine();
                    boolean isDeadlineCorrect = false;
                    do {
                        try {
                            System.out.print("Enter new deadline (Format: yyyy-mm-dd): ");
                            assignmentDeadline = scanner.nextLine();
                            deadline = LocalDate.parse(assignmentDeadline);
                            isDeadlineCorrect = true;
                        } catch (DateTimeParseException e) {
                            System.out.println(RED + "Deadline format is not correct!" + RESET);
                        }
                    } while (!isDeadlineCorrect);
                    //TODO: find assignment with this ID
                    //temp actions-----------------
                    assignment.setDeadline(deadline);
                    //-----------------------------
                    System.out.println(GREEN + "Deadline has been successfully updated." + RESET);
                    isAdminActionChosen = true;
                    break;
                case "5": //add assignment
                    clear();
                    System.out.println(YELLOW + "Adding assignment..." + RESET);
                    System.out.print("Enter Course ID: ");
                    courseID = scanner.nextLine();
                    System.out.print("Enter Assignment ID: ");
                    assignmentID = scanner.nextLine();
                    //TODO: find course and assignment with this ID
                    //temp actions-----------------
                    course.getListOfAssignments().add(assignment);
                    //-----------------------------
                    System.out.println(GREEN + "Assignment has been successfully added." + RESET);
                    isAdminActionChosen = true;
                    break;
                case "6": //remove assignment
                    clear();
                    System.out.println(YELLOW + "Removing student..." + RESET);
                    System.out.print("Enter Course ID: ");
                    courseID = scanner.nextLine();
                    System.out.print("Enter Assignment ID: ");
                    assignmentID = scanner.nextLine();
                    //TODO: find course and assignment with this ID
                    //temp actions-----------------
                    course.getListOfAssignments().remove(assignment);
                    //-----------------------------
                    System.out.println(GREEN + "Assignment has been successfully removed." + RESET);
                    isAdminActionChosen = true;
                    break;
                case "7": //add teacher
                    clear();
                    System.out.println(YELLOW + "Adding teacher..." + RESET);
                    System.out.print("Enter Teacher first name: ");
                    teacherFirstName = scanner.nextLine();
                    System.out.print("Enter Teacher last name: ");
                    teacherLastName = scanner.nextLine();
                    System.out.print("Enter Teacher ID: ");
                    teacherID = scanner.nextLine();
                    //TODO: add teacher to database
                    //temp actions-----------------
                    Teacher newTEacher = new Teacher(teacherID, teacherFirstName, teacherLastName);
                    //-----------------------------
                    System.out.println(GREEN + "Teacher \"" + teacher.getFirstName() + " " + teacherLastName
                            + "\" has been " +
                            "successfully added");
                    isAdminActionChosen = true;
                    break;
                case "8": //remove teacher
                    clear();
                    System.out.println(YELLOW + "Removing teacher..." + RESET);
                    System.out.print("Enter Teacher ID: ");
                    teacherID = scanner.nextLine();
                    //TODO: find teacher with this ID and check if it exits
                    //temp actions---------------------------
                    teacher = null;
                    //---------------------------------------
                    System.out.println(GREEN + "Teacher with ID: " + teacherID +
                            " has been successfully removed" + RESET);
                    isAdminActionChosen = true;
                    break;
                case "9": //add course
                    clear();
                    System.out.println(YELLOW + "Adding course..." + RESET);
                    System.out.print("Enter Course name: ");
                    courseName = scanner.nextLine();
                    System.out.print("Enter Course ID: ");
                    courseID = scanner.nextLine();
                    System.out.print("Enter Teacher ID: ");
                    teacherID = scanner.nextLine();
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
                    boolean isExamDate = false;
                    do {
                        try {
                            System.out.print("Enter exam date (Format: yyyy-mm-dd): ");
                            courseExamDate = scanner.nextLine();
                            dateOfExam = LocalDate.parse(courseExamDate);
                            isExamDate = true;
                        } catch (DateTimeParseException e) {
                            System.out.println(RED + "Exam date format is not correct!" + RESET);
                        }
                    } while (!isExamDate);
                    //TODO: validate teacherID, add course to database
                    //temp actions-----------------
                    Course newCourse = new Course(courseName, courseID, teacher, numberOfUnits, dateOfExam);
                    //-----------------------------
                    System.out.println(GREEN + "Course \"" + course.getName() + "\" has been " +
                            "successfully added");
                    isAdminActionChosen = true;
                    break;
                case "10": //remove course
                    clear();
                    System.out.println(YELLOW + "Removing course..." + RESET);
                    System.out.print("Enter Course ID: ");
                    courseID = scanner.nextLine();
                    //TODO: find course with this ID and check if it exits
                    //temp actions---------------------------
                    course = null;
                    //---------------------------------------
                    System.out.println(GREEN + "Course with ID: " + courseID +
                            " has been successfully removed" + RESET);
                    isAdminActionChosen = true;
                    break;
                default:
                    System.out.println(RED + "Invalid action, please choose again." + RESET);
                    System.out.print("Enter a number: ");
                    break;
            }
        }
        
        /*به نظرم بعد از انتخاب عملکرد ایدی استاد رو دریافت کنیم
        می تونیم بعد از انتخاب role استاد و قبل از پیام
        خوش آمد هم دریافت کنیم.*/
        //Teacher menu
//        if ("Teacher".equalsIgnoreCase(currentRole)) {
//            System.out.println("""
//                    1. Add student
//                    2. remove student
//                    3. Give mark to a student
//                    4. Change an assignment deadline
//                    5. Add assignment
//                    6. Remove assignment
//                    """);
//            System.out.print("Enter a number: ");
//        }
//        int teacherAction = scanner.nextInt();
//        switch (teacherAction) {
//            case 1:
//                System.out.println("adding student...");
//
//        }

    }
}