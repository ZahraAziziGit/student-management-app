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

        String studentPath = filePath + "/students_data.txt";
        String studentTempPath = filePath + "/temp_students_data.txt";
        String assignmentPath = filePath + "/assignments_data.txt";
        String assignmentTempPath = filePath + "/temp_assignments_data.txt";
        String teacherPath = filePath + "/teachers_data.txt";
        String teacherTempPath = filePath + "/temp_teachers_data.txt";
        String coursePath = filePath + "/courses_data.txt";
        String courseTempPath = filePath + "/temp_courses_data.txt";

        //for windows, uncomment this part and comment the previous part.
    /*  String studentPath = filePath + "\\students_data.txt";
        String studentTempPath = filePath + "\\temp_students_data.txt";
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
                        IdFinder.findTeacherByID(teacherID, teacherFile);
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
                        12. Add a student to a course
                        13. Remove a student from a course
                        14. Change a course status
                        15. Change a course exam date
                        16. Exit
                        """);
                System.out.print("Enter a number: ");

                boolean isAdminActionChosen = false;
                while (!isAdminActionChosen) {
                    String adminAction = scanner.nextLine();

                    //variables
                    String studentFirstName, studentLastName, studentID,
                            assignmentName, assignmentID, assignmentDeadline = "",
                            teacherFirstName, teacherLastName, teacherID,
                            courseName, courseID, courseExamDate = "";
                    double mark = -1;
                    int numberOfUnits = 0;

                    String[] studentData;
                    String[] courseData;
                    String[] assignmentData;
                    String[] teacherData;

                    switch (adminAction) {
                        case "1":
                            clear();
                            System.out.println(YELLOW + "Adding a student..." + RESET);

                            System.out.print("Enter Student first name: ");
                            studentFirstName = scanner.nextLine();
                            System.out.print("Enter Student last name: ");
                            studentLastName = scanner.nextLine();
                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();

                            StoreData.storeStudent(studentFirstName, studentLastName, studentID,
                                    0, 0,
                                    new ArrayList<>(), 0.0, new HashMap<>(), studentFile);

                            System.out.println(GREEN + "Student (ID: " + studentID + ") has been successfully added." + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "2":
                            clear();
                            System.out.println(YELLOW + "Removing a student..." + RESET);

                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();

                            try {
                                studentData = IdFinder.findStudentByID(studentID, studentFile);
                                String[] studentCoursesIds = studentData[5].substring(1, studentData[5].length() - 1).split("~");
                                for (String courseId : studentCoursesIds) {
                                    courseData = IdFinder.findCourseByID(courseId, courseFile);
                                    int numOfStudents = Integer.parseInt(courseData[5]);
                                    numOfStudents--;
                                    String[] studentsIds = courseData[6].substring(1, courseData[6].length() - 1).split("~");
                                    List<String> listOfStudentsIds = new ArrayList<>();
                                    Collections.addAll(listOfStudentsIds, studentsIds);
                                    listOfStudentsIds.remove(studentID);
                                    UpdateData.updateCourseData(courseFile, courseTempFile, courseId, listOfStudentsIds, numOfStudents);

                                    String[] courseMarksForStudentRemove = courseData[3].split("\\*");
                                    if (courseMarksForStudentRemove.length > 1) {
                                        Map<String, Double> newCourseMarksStudentRemove = new HashMap<>();
                                        for (String markDetails : courseMarksForStudentRemove) {
                                            newCourseMarksStudentRemove.put(markDetails.split("#")[0], Double.valueOf(markDetails.split("#")[1]));
                                        }
                                        newCourseMarksStudentRemove.remove(studentID);
                                        UpdateData.updateCourseData(courseFile, courseTempFile, courseId, newCourseMarksStudentRemove);
                                    }
                                }

                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }

                            UpdateData.updateStudentData(studentFile, studentTempFile, studentID);

                            System.out.println(GREEN + "Student (ID: " + studentID + ") has been successfully removed." + RESET);
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
                                studentData = IdFinder.findStudentByID(studentID, studentFile);
                                courseData = IdFinder.findCourseByID(courseID, courseFile);
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

                            String[] courseMarks = courseData[3].substring(1, courseData[3].length() - 1).split("\\*");
                            Map<String, Double> newCourseMarks = new HashMap<>();
                            if (courseMarks.length > 1) {
                                for (String markData : courseMarks) {
                                    newCourseMarks.put(markData.split("#")[0], Double.parseDouble(markData.split("#")[1]));
                                }
                            }
                            newCourseMarks.put(courseID, mark);

                            String[] studentMarks = studentData[7].substring(1, studentData[7].length() - 1).split("~");
                            Map<String, Double> newStudentMarks = new HashMap<>();
                            if (studentMarks.length > 1) {
                                for (String markData : studentMarks) {
                                    newStudentMarks.put(markData.split("=")[0], Double.parseDouble(markData.split("=")[1]));
                                }
                            }
                            newStudentMarks.put(courseID, mark);

                            double newStudentAvg = 0.0;
                            int sumOfUnits = 0;
                            for (String courseId : newStudentMarks.keySet()) {
                                try {
                                    String[] coursesForMarkData = IdFinder.findCourseByID(courseId, courseFile);
                                    int courseUnits = Integer.parseInt(coursesForMarkData[4]);
                                    sumOfUnits += courseUnits;
                                    newStudentAvg += courseUnits * newStudentMarks.get(courseId);
                                } catch (NotFoundException e) {
                                    System.out.println(e.getMessage());
                                    isAdminActionChosen = true;
                                    break;
                                }
                            }
                            newStudentAvg /= sumOfUnits;

                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID, newCourseMarks);
                            UpdateData.updateStudentData(studentFile, studentTempFile, studentID,
                                    newStudentAvg, newStudentMarks);

                            System.out.println(GREEN + "Student (ID: " + studentID + ") mark (" + mark + " for Course (ID:"
                                    + courseID + ") has been successfully added." + RESET);
                            scanner.nextLine(); //clear buffer
                            isAdminActionChosen = true;
                            break;

                        case "4":
                            clear();
                            System.out.println(YELLOW + "Adding an assignment..." + RESET);

                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();
                            System.out.print("Enter Assignment name: ");
                            assignmentName = scanner.nextLine();
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();

                            try {
                                courseData = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }

                            boolean isDeadlineCorrect = false;
                            do {
                                try {
                                    System.out.print("Enter deadline (Format: yyyy-mm-dd): ");
                                    assignmentDeadline = scanner.nextLine();
                                    LocalDate.parse(assignmentDeadline);
                                    isDeadlineCorrect = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println(RED + "Deadline format is not correct!" + RESET);
                                }
                            } while (!isDeadlineCorrect);

                            int numOfCourseAssignment = Integer.parseInt(courseData[8]);
                            numOfCourseAssignment++;
                            String[] assignmentIds = courseData[9].substring(1, courseData[9].length() - 1).split("~");
                            List<String> courseAssignments = new ArrayList<>();
                            if (assignmentIds.length > 2)
                                Collections.addAll(courseAssignments, assignmentIds);
                            courseAssignments.add(assignmentID);

                            StoreData.storeAssignment(assignmentID, assignmentDeadline, true, courseID, assignmentName, assignmentFile);
                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID, numOfCourseAssignment, courseAssignments);

                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") has been successfully added." + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "5":
                            clear();
                            System.out.println(YELLOW + "Removing an assignment..." + RESET);

                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();

                            try {
                                assignmentData = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
                                courseData = IdFinder.findCourseByID(assignmentData[3], courseFile);
                                int numOfAssignments = Integer.parseInt(courseData[8]);
                                numOfAssignments--;
                                assignmentIds = courseData[9].substring(1, courseData[9].length() - 1).split("~");
                                List<String> listOfAssignmentIds = new ArrayList<>();
                                Collections.addAll(listOfAssignmentIds, assignmentIds);
                                listOfAssignmentIds.remove(assignmentID);
                                UpdateData.updateCourseData(courseFile, courseTempFile, assignmentData[3],
                                        numOfAssignments, listOfAssignmentIds);

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

                            boolean isNewDeadlineCorrect = false;
                            do {
                                try {
                                    System.out.print("Enter new deadline (Format: yyyy-mm-dd): ");
                                    assignmentDeadline = scanner.nextLine();
                                    LocalDate.parse(assignmentDeadline);
                                    isNewDeadlineCorrect = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println(RED + "Deadline format is not correct!" + RESET);
                                }
                            } while (!isNewDeadlineCorrect);

                            UpdateData.updateAssignmentData(assignmentFile, assignmentTempFile, assignmentID, assignmentDeadline);


                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") deadline has been" +
                                    " successfully updated to " + assignmentDeadline + "." + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "7":
                            clear();
                            System.out.println(YELLOW + "Changing an assignment status..." + RESET);

                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();

                            try {
                                assignmentData = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }

                            UpdateData.updateAssignmentData(assignmentFile, assignmentTempFile, assignmentID, (!assignmentData[2].equals("true")));

                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") status has been successfully" +
                                    " changed from " + (assignmentData[2].equals("true") ? "active" : "not active") + " to " +
                                    (!assignmentData[2].equals("true") ? "active." : "not active.") + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "8":
                            clear();
                            System.out.println(YELLOW + "Adding a teacher..." + RESET);

                            System.out.print("Enter Teacher first name: ");
                            teacherFirstName = scanner.nextLine();
                            System.out.print("Enter Teacher last name: ");
                            teacherLastName = scanner.nextLine();
                            System.out.print("Enter Teacher ID: ");
                            teacherID = scanner.nextLine();

                            StoreData.storeTeacher(teacherFirstName, teacherLastName, teacherID, 0,
                                    new ArrayList<>(), teacherFile);

                            System.out.println(GREEN + "Teacher (ID: " + teacherID + ") has been successfully added" + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "9":
                            clear();
                            System.out.println(YELLOW + "Removing a teacher..." + RESET);

                            System.out.print("Enter Teacher ID: ");
                            teacherID = scanner.nextLine();
                            try {
                                teacherData = IdFinder.findTeacherByID(teacherID, teacherFile);
                                String[] teacherCourses = teacherData[4].substring(1, teacherData[4].length() - 1).split("~");
                                List<String> listOfTeacherCourses = new ArrayList<>();
                                Collections.addAll(listOfTeacherCourses, teacherCourses);
                                for (String courseId : listOfTeacherCourses) {
                                    UpdateData.updateCourseData(courseFile, courseId, "-", courseTempFile);
                                }

                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }

                            UpdateData.updateTeacherData(teacherFile, teacherTempFile, teacherID);
                            System.out.println(GREEN + "Teacher (ID: " + teacherID + ") has been successfully removed" + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "10":
                            clear();
                            System.out.println(YELLOW + "Adding a course..." + RESET);

                            System.out.print("Enter Course name: ");
                            courseName = scanner.nextLine();
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            System.out.print("Enter Teacher ID: ");
                            teacherID = scanner.nextLine();

                            try {
                                teacherData = IdFinder.findTeacherByID(teacherID, teacherFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }

                            int teacherNumOfCourses = Integer.parseInt(teacherData[3]);
                            teacherNumOfCourses++;
                            String[] teacherCourses = teacherData[4].substring(1, teacherData[4].length() - 1).split("~");
                            List<String> listOfTeacherCourses = new ArrayList<>();
                            Collections.addAll(listOfTeacherCourses, teacherCourses);
                            listOfTeacherCourses.add(courseID);

                            boolean isUnitInt = false;
                            do {
                                try {
                                    System.out.print("Enter a number of units: ");
                                    numberOfUnits = scanner.nextInt();
                                    isUnitInt = true;
                                } catch (InputMismatchException e) {
                                    System.out.println(RED + "Number of units must be an integer!" + RESET);
                                    scanner.nextLine(); //clear buffer
                                }
                            } while (!isUnitInt);

                            scanner.nextLine(); //clear buffer
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

                            StoreData.storeCourse(courseName, courseID, teacherID, new HashMap<>(), numberOfUnits,
                                    0, new ArrayList<>(), true, 0, new ArrayList<>(),
                                    courseExamDate, courseFile);
                            UpdateData.updateTeacherData(teacherFile, teacherTempFile, teacherID,
                                    teacherNumOfCourses, listOfTeacherCourses);

                            System.out.println(GREEN + "Course \"" + courseName +
                                    "\" (ID: " + courseID + ") has been successfully added" + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "11":
                            clear();
                            System.out.println(YELLOW + "Removing a course..." + RESET);

                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();
                            try {
                                courseData = IdFinder.findCourseByID(courseID, courseFile);

                                String courseTeacherId = courseData[2];
                                if (!courseTeacherId.equals("-")) {
                                    teacherData = IdFinder.findTeacherByID(courseTeacherId, teacherFile);
                                    int teacherNumOfLessons = Integer.parseInt(teacherData[3]);
                                    teacherNumOfLessons--;
                                    String[] coursesIds = teacherData[4].substring(1, teacherData[4].length() - 1).split("~");
                                    List<String> listOfCoursesIds = new ArrayList<>();
                                    Collections.addAll(listOfCoursesIds, coursesIds);
                                    listOfCoursesIds.remove(courseID);
                                    UpdateData.updateTeacherData(teacherFile, teacherTempFile, courseTeacherId,
                                            teacherNumOfLessons, listOfCoursesIds);
                                }

                                String[] studentIds = courseData[6].substring(1, courseData[6].length() - 1).split("~");

                                List<String> courseStudents = new ArrayList<>();
                                Collections.addAll(courseStudents, studentIds);
                                for (String studentId : courseStudents) {
                                    studentData = IdFinder.findStudentByID(studentId, studentFile);
                                    int numOfStudentsCourses = Integer.parseInt(studentData[3]);
                                    numOfStudentsCourses--;
                                    int numOfStudentUnits = Integer.parseInt(studentData[4]);
                                    numOfStudentUnits -= Integer.parseInt(courseData[4]);
                                    String[] studentCourses = studentData[5].substring(1, studentData[5].length() - 1).split("~");
                                    List<String> studentCoursesIds = new ArrayList<>();
                                    Collections.addAll(studentCoursesIds, studentCourses);
                                    studentCoursesIds.remove(courseID);
                                    UpdateData.updateStudentData(studentFile, studentTempFile, studentId,
                                            numOfStudentsCourses, numOfStudentUnits, studentCoursesIds);


                                    String[] studentMarksForCourseDelete = studentData[7].substring(1, studentData[7].length() - 1).split("~");
                                    Map<String, Double> stuMarks = new HashMap<>();
                                    for (String stuMarkDetails : studentMarksForCourseDelete) {
                                        stuMarks.put(stuMarkDetails.split("=")[0], Double.valueOf(stuMarkDetails.split("=")[1]));
                                    }
                                    stuMarks.remove(courseID);
                                    newStudentAvg = 0.0;
                                    sumOfUnits = 0;
                                    for (String courseId : stuMarks.keySet()) {
                                        try {
                                            String[] coursesForMarkData = IdFinder.findCourseByID(courseId, courseFile);
                                            int courseUnits = Integer.parseInt(coursesForMarkData[4]);
                                            sumOfUnits += courseUnits;
                                            newStudentAvg += courseUnits * stuMarks.get(courseId);
                                        } catch (NotFoundException e) {
                                            System.out.println(e.getMessage());
                                            isAdminActionChosen = true;
                                            break;
                                        }
                                    }
                                    if (sumOfUnits != 0)
                                        newStudentAvg /= sumOfUnits;
                                    else
                                        newStudentAvg = 0.0;
                                    UpdateData.updateStudentData(studentFile, studentTempFile, studentId,
                                            newStudentAvg, stuMarks);

                                }


                                assignmentIds = courseData[9].substring(1, courseData[9].length() - 1).split("~");

                                courseAssignments = new ArrayList<>();
                                Collections.addAll(courseAssignments, assignmentIds);
                                for (String assignmentId : courseAssignments) {
                                    UpdateData.updateAssignmentCourse(assignmentFile, assignmentTempFile,
                                            assignmentId, "-");
                                }

                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID);

                            System.out.println(GREEN + "Course (ID: " + courseID + ") has been successfully removed" + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "12":
                            clear();
                            System.out.println(YELLOW + "Adding student to a course..." + RESET);

                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();

                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();

                            try {
                                courseData = IdFinder.findCourseByID(courseID, courseFile);
                                studentData = IdFinder.findStudentByID(studentID, studentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }

                            int numOfStudentsCourses = Integer.parseInt(studentData[3]);
                            numOfStudentsCourses++;
                            int numOfStudentUnits = Integer.parseInt(studentData[4]);
                            numOfStudentUnits += Integer.parseInt(courseData[4]);
                            String[] studentCourses = studentData[5].substring(1, studentData[5].length() - 1).split("~");
                            List<String> studentCoursesIds = new ArrayList<>();
                            Collections.addAll(studentCoursesIds, studentCourses);
                            studentCoursesIds.add(courseID);
                            UpdateData.updateStudentData(studentFile, studentTempFile, studentID,
                                    numOfStudentsCourses, numOfStudentUnits, studentCoursesIds);

                            int numOfStudents = Integer.parseInt(courseData[5]);
                            numOfStudents++;
                            String[] studentsIds = courseData[6].substring(1, courseData[6].length() - 1).split("~");
                            List<String> listOfStudentsIds = new ArrayList<>();
                            Collections.addAll(listOfStudentsIds, studentsIds);
                            listOfStudentsIds.add(studentID);
                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID, listOfStudentsIds, numOfStudents);

                            System.out.println(GREEN + "Student (ID: " + studentID + ") has been successfully added to Course " +
                                    "(ID:" + courseID + ")." + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "13":
                            clear();
                            System.out.println(YELLOW + "Removing student from a course..." + RESET);

                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();

                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();

                            try {
                                courseData = IdFinder.findCourseByID(courseID, courseFile);
                                studentData = IdFinder.findStudentByID(studentID, studentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }

                            numOfStudentsCourses = Integer.parseInt(studentData[3]);
                            numOfStudentsCourses--;
                            numOfStudentUnits = Integer.parseInt(studentData[4]);
                            numOfStudentUnits -= Integer.parseInt(courseData[4]);
                            studentCourses = studentData[5].substring(1, studentData[5].length() - 1).split("~");
                            studentCoursesIds = new ArrayList<>();
                            Collections.addAll(studentCoursesIds, studentCourses);
                            studentCoursesIds.remove(courseID);
                            UpdateData.updateStudentData(studentFile, studentTempFile, studentID,
                                    numOfStudentsCourses, numOfStudentUnits, studentCoursesIds);

                            String[] studentMarksForCourseDelete = studentData[7].substring(1, studentData[7].length() - 1).split("~");
                            if (studentMarksForCourseDelete.length > 1) {
                                Map<String, Double> stuMarks = new HashMap<>();
                                for (String stuMarkDetails : studentMarksForCourseDelete) {
                                    System.out.println(stuMarkDetails);
                                    stuMarks.put(stuMarkDetails.split("=")[0], Double.valueOf(stuMarkDetails.split("=")[1]));
                                }
                                stuMarks.remove(courseID);
                                newStudentAvg = 0.0;
                                sumOfUnits = 0;
                                for (String courseId : stuMarks.keySet()) {
                                    try {
                                        String[] coursesForMarkData = IdFinder.findCourseByID(courseId, courseFile);
                                        int courseUnits = Integer.parseInt(coursesForMarkData[4]);
                                        sumOfUnits += courseUnits;
                                        newStudentAvg += courseUnits * stuMarks.get(courseId);
                                    } catch (NotFoundException e) {
                                        System.out.println(e.getMessage());
                                        isAdminActionChosen = true;
                                        break;
                                    }
                                }
                                newStudentAvg /= sumOfUnits;
                                UpdateData.updateStudentData(studentFile, studentTempFile, studentID,
                                        newStudentAvg, stuMarks);
                            }

                            numOfStudents = Integer.parseInt(courseData[5]);
                            numOfStudents--;
                            studentsIds = courseData[6].substring(1, courseData[6].length() - 1).split("~");
                            listOfStudentsIds = new ArrayList<>();
                            Collections.addAll(listOfStudentsIds, studentsIds);
                            listOfStudentsIds.remove(studentID);
                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID, listOfStudentsIds, numOfStudents);

                            String[] courseMarksForStudentRemove = courseData[3].split("\\*");
                            if (courseMarksForStudentRemove.length > 1) {
                                Map<String, Double> newCourseMarksStudentRemove = new HashMap<>();
                                for (String markDetails : courseMarksForStudentRemove) {
                                    newCourseMarksStudentRemove.put(markDetails.split("#")[0], Double.valueOf(markDetails.split("#")[1]));
                                }
                                newCourseMarksStudentRemove.remove(studentID);
                                UpdateData.updateCourseData(courseFile, courseTempFile, courseID, newCourseMarksStudentRemove);
                            }
                            System.out.println(GREEN + "Student (ID: " + studentID + ") has been successfully removed from Course " +
                                    "(ID:" + courseID + ")." + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "14":
                            clear();
                            System.out.println(YELLOW + "Changing course status..." + RESET);

                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();

                            try {
                                courseData = IdFinder.findCourseByID(courseID, courseFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isAdminActionChosen = true;
                                break;
                            }
                            String courseStatus = courseData[7];
                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID, (!courseStatus.equals("true")));

                            System.out.println(GREEN + "Course (ID: " + courseID +
                                    ") status has been successfully changed from " + (courseStatus.equals("true") ? "active" : "not active")
                                    + " to " + (!courseStatus.equals("true") ? "active." : "not active.") + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "15":
                            clear();
                            System.out.println(YELLOW + "Changing course exam date..." + RESET);

                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();

                            isExamDateCorrect = false;
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

                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID, courseExamDate);

                            System.out.println(GREEN + "Course (ID: " + courseID + ") exam date has been successfully changed to " + courseExamDate + "." + RESET);
                            isAdminActionChosen = true;
                            break;

                        case "16":
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
                    String studentID, courseID, assignmentID, assignmentDeadline = "";
                    double mark = -1;
                    LocalDate deadline = LocalDate.now();
                    Student student;
                    Course course;
                    Assignment assignment;

                    String[] studentData;
                    String[] courseData;
                    String[] assignmentData;
                    String[] teacherData;

                    switch (teacherAction) {
                        case "1":
                            clear();
                            System.out.println(YELLOW + "Adding student to a course..." + RESET);

                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();

                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();

                            try {
                                courseData = IdFinder.findCourseByID(courseID, courseFile);
                                studentData = IdFinder.findStudentByID(studentID, studentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }

                            int numOfStudentsCourses = Integer.parseInt(studentData[3]);
                            numOfStudentsCourses++;
                            int numOfStudentUnits = Integer.parseInt(studentData[4]);
                            numOfStudentUnits += Integer.parseInt(courseData[4]);
                            String[] studentCourses = studentData[5].substring(1, studentData[5].length() - 1).split("~");
                            List<String> studentCoursesIds = new ArrayList<>();
                            Collections.addAll(studentCoursesIds, studentCourses);
                            studentCoursesIds.add(courseID);
                            UpdateData.updateStudentData(studentFile, studentTempFile, studentID,
                                    numOfStudentsCourses, numOfStudentUnits, studentCoursesIds);

                            int numOfStudents = Integer.parseInt(courseData[5]);
                            numOfStudents++;
                            String[] studentsIds = courseData[6].substring(1, courseData[6].length() - 1).split("~");
                            List<String> listOfStudentsIds = new ArrayList<>();
                            Collections.addAll(listOfStudentsIds, studentsIds);
                            listOfStudentsIds.add(studentID);
                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID, listOfStudentsIds, numOfStudents);

                            System.out.println(GREEN + "Student (ID: " + studentID +
                                    ") has been successfully added to course (ID: " + courseID + ")." + RESET);
                            isTeacherActionChosen = true;
                            break;

                        case "2":
                            clear();
                            System.out.println(YELLOW + "Removing student from a course..." + RESET);

                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();

                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();

                            try {
                                courseData = IdFinder.findCourseByID(courseID, courseFile);
                                studentData = IdFinder.findStudentByID(studentID, studentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }

                            numOfStudentsCourses = Integer.parseInt(studentData[3]);
                            numOfStudentsCourses--;
                            numOfStudentUnits = Integer.parseInt(studentData[4]);
                            numOfStudentUnits -= Integer.parseInt(courseData[4]);
                            studentCourses = studentData[5].substring(1, studentData[5].length() - 1).split("~");
                            studentCoursesIds = new ArrayList<>();
                            Collections.addAll(studentCoursesIds, studentCourses);
                            studentCoursesIds.remove(courseID);
                            UpdateData.updateStudentData(studentFile, studentTempFile, studentID,
                                    numOfStudentsCourses, numOfStudentUnits, studentCoursesIds);

                            numOfStudents = Integer.parseInt(courseData[5]);
                            numOfStudents--;
                            studentsIds = courseData[6].substring(1, courseData[6].length() - 1).split("~");
                            listOfStudentsIds = new ArrayList<>();
                            Collections.addAll(listOfStudentsIds, studentsIds);
                            listOfStudentsIds.remove(studentID);
                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID, listOfStudentsIds, numOfStudents);

                            System.out.println(GREEN + "Student (ID: " + studentID +
                                    ") has been successfully removed from course (ID: " + courseID + ")." + RESET);
                            isTeacherActionChosen = true;
                            break;

                        case "3":
                            clear();
                            System.out.println(YELLOW + "Giving mark to a student..." + RESET);

                            System.out.print("Enter Student ID: ");
                            studentID = scanner.nextLine();
                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();

                            try {
                                studentData = IdFinder.findStudentByID(studentID, studentFile);
                                courseData = IdFinder.findCourseByID(courseID, courseFile);
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

                            String[] courseMarks = courseData[3].substring(1, courseData[3].length() - 1).split("\\*");
                            Map<String, Double> newCourseMarks = new HashMap<>();
                            for (String markData : courseMarks) {
                                newCourseMarks.put(markData.split("#")[0], Double.parseDouble(markData.split("#")[1]));
                            }
                            newCourseMarks.put(courseID, mark);

                            String[] studentMarks = studentData[7].substring(1, studentData[7].length() - 1).split("~");
                            Map<String, Double> newStudentMarks = new HashMap<>();
                            for (String markData : studentMarks) {
                                newStudentMarks.put(markData.split("=")[0], Double.parseDouble(markData.split("=")[1]));
                            }
                            newStudentMarks.put(courseID, mark);

                            double newStudentAvg = 0.0;
                            int sumOfUnits = 0;
                            for (String courseId : newStudentMarks.keySet()) {
                                try {
                                    String[] coursesForMarkData = IdFinder.findCourseByID(courseId, courseFile);
                                    int courseUnits = Integer.parseInt(coursesForMarkData[4]);
                                    sumOfUnits += courseUnits;
                                    newStudentAvg += courseUnits * newStudentMarks.get(courseId);
                                } catch (NotFoundException e) {
                                    System.out.println(e.getMessage());
                                    isTeacherActionChosen = true;
                                    break;
                                }
                            }
                            newStudentAvg /= sumOfUnits;

                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID, newCourseMarks);
                            UpdateData.updateStudentData(studentFile, studentTempFile, studentID,
                                    newStudentAvg, newStudentMarks);

                            System.out.println(GREEN + "Student (ID: " + studentID + ") mark (" + mark + " for Course (ID:"
                                    + courseID + ") has been successfully added." + RESET);
                            scanner.nextLine(); //clear buffer
                            isTeacherActionChosen = true;
                            break;

                        case "4":
                            clear();
                            System.out.println(YELLOW + "Adding an assignment to a course..." + RESET);

                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();

                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();

                            try {
                                courseData = IdFinder.findCourseByID(courseID, courseFile);
                                assignmentData = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }

                            int numOfCourseAssignment = Integer.parseInt(courseData[8]);
                            numOfCourseAssignment++;
                            String[] assignmentIds = courseData[9].substring(1, courseData[9].length() - 1).split("~");
                            List<String> courseAssignments = new ArrayList<>();
                            Collections.addAll(courseAssignments, assignmentIds);
                            courseAssignments.add(assignmentID);
                            UpdateData.updateAssignmentCourse(assignmentFile, assignmentTempFile, assignmentID, courseID);
                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID, numOfCourseAssignment, courseAssignments);

                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") has been successfully" +
                                    " added to Course (ID:" + courseID + ")." + RESET);
                            isTeacherActionChosen = true;
                            break;

                        case "5":
                            clear();
                            System.out.println(YELLOW + "Removing an assignment from a course..." + RESET);

                            System.out.print("Enter Course ID: ");
                            courseID = scanner.nextLine();

                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();

                            try {
                                courseData = IdFinder.findCourseByID(courseID, courseFile);
                                assignmentData = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
                            } catch (NotFoundException e) {
                                System.out.println(e.getMessage());
                                isTeacherActionChosen = true;
                                break;
                            }

                            numOfCourseAssignment = Integer.parseInt(courseData[8]);
                            numOfCourseAssignment--;
                            assignmentIds = courseData[9].substring(1, courseData[9].length() - 1).split("~");
                            courseAssignments = new ArrayList<>();
                            Collections.addAll(courseAssignments, assignmentIds);
                            courseAssignments.remove(assignmentID);
                            UpdateData.updateAssignmentCourse(assignmentFile, assignmentTempFile, assignmentID, courseID);
                            UpdateData.updateCourseData(courseFile, courseTempFile, courseID, numOfCourseAssignment, courseAssignments);

                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") has been successfully" +
                                    " removed from Course (ID:" + courseID + ")." + RESET);
                            isTeacherActionChosen = true;
                            break;

                        case "6":
                            clear();
                            System.out.println(YELLOW + "Changing an assignment deadline..." + RESET);

                            System.out.print("Enter Assignment ID: ");
                            assignmentID = scanner.nextLine();

                            try {
                                assignmentData = IdFinder.findAssignmentByID(assignmentID, assignmentFile);
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

                            UpdateData.updateAssignmentData(assignmentFile, assignmentTempFile, assignmentID, assignmentDeadline);


                            System.out.println(GREEN + "Assignment (ID: " + assignmentID + ") deadline has been" +
                                    " successfully updated to " + assignmentDeadline + "." + RESET);
                            isTeacherActionChosen = true;
                            break;

                        case "7":
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