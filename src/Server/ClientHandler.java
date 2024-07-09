package Server;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ClientHandler extends Thread {
    Socket socket;
    DataOutputStream dos;
    DataInputStream dis;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }

    public String listener() throws IOException {
        try {
            System.out.println("Listener started");
            StringBuilder request = new StringBuilder();
            int index = dis.read();
            while (index != 0) {
                request.append((char) index);
                index = dis.read();
            }
            System.out.println("listener has read command successfully: { " + request + " }");
            return request.toString();
        } catch (IOException e) {
            System.out.println("Error in listener : " + e.getMessage());
        }
        return "Error!";
    }

    public void writer(String write) throws IOException {
        try {
            dos.writeBytes(write);
            dos.flush();
            dos.close();
            dis.close();
            socket.close();
            System.out.println("Writer finished and response was sent to server: { " + write + " }");
        } catch (IOException e) {
            System.out.println("Error in writer : " + e.getMessage());
        }
    }

    @Override
    public void run() {
        super.run();
        String command;
        try {

            //database directory
            String filePath = "./Database";
            //String filePath = ".\\Database"; //for windows, uncomment this and comment the previous line.

            String studentPath = filePath + "/students_data.txt";
            String assignmentPath = filePath + "/assignments_data.txt";
            String teacherPath = filePath + "/teachers_data.txt";
            String coursePath = filePath + "/courses_data.txt";
            String userPath = filePath + "/users_data.txt";
            String userTempPath = filePath + "/users_temp_data.txt";
            String currentUserPath = filePath + "/current_user.txt";

            //for windows, uncomment this part and comment the previous part.
        /*  String studentPath = filePath + "\\students_data.txt";
            String assignmentPath = filePath + "\\assignments_data.txt";
            String teacherPath = filePath + "\\teachers_data.txt";
            String coursePath = filePath + "\\courses_data.txt";
            String userPath = filePath + "\\users_data.txt";
            String userTampPath = filePath + "\\users_temp_data.txt";*/

            File studentFile = new File(studentPath);
            File assignmentFile = new File(assignmentPath);
            File teacherFile = new File(teacherPath);
            File courseFile = new File(coursePath);
            File userFile = new File(userPath);
            File userTempFile = new File(userTempPath);
            File currentUserFile = new File(currentUserPath);

            userFile.createNewFile();

            String[] userDataFromServer;
            String studentUsername, studentId, studentPassword, studentUsernameOrId;

            String[] userDataFromDB;
            String usernameFromDB, userIdFromDB, userPasswordFromDB;

            String currentUserId = "";

            String[] studentDetailsFromDB;

            command = listener();
            String action = command.split("~")[0];

            Scanner userReader, studentReader;

            switch (action) {

                case "signup":
                    userDataFromServer = command.split("~");   //example: signup~testUser~123456789~password
                    studentUsername = userDataFromServer[1];
                    studentId = userDataFromServer[2];
                    studentPassword = userDataFromServer[3];

                    boolean isUsernameDuplicate = false;
                    userReader = new Scanner(userFile);
                    while (userReader.hasNext()) {
                        userDataFromDB = userReader.nextLine().split("~");
                        usernameFromDB = userDataFromDB[0].split(":")[1];
                        if (usernameFromDB.equals(studentUsername)) {
                            isUsernameDuplicate = true;
                            break;
                        }
                    }

                    if (!isUsernameDuplicate) {
                        boolean isStudentFound = false;
                        studentReader = new Scanner(studentFile);
                        while (studentReader.hasNext()) {
                            studentDetailsFromDB = studentReader.nextLine().split(",");
                            if (studentDetailsFromDB[2].split(":")[1].equals(studentId)) {
                                isStudentFound = true;
                            }
                        }
                        studentReader.close();

                        if (isStudentFound) {
                            writer("found");
                            Writer writer = new FileWriter(userFile, true);
                            writer.write("username:" + studentUsername + "~id:" + studentId + "~password:" + studentPassword + "\n");
                            writer.flush();
                            writer.close();
                        } else
                            writer("not found");
                        break;
                    } else {
                        writer("duplicate");
                    }
                    userReader.close();
                    break;

                case "login":
                    userDataFromServer = command.split("~");   //example: login~testUser~password
                    studentUsernameOrId = userDataFromServer[1];
                    studentPassword = userDataFromServer[2];

                    boolean isUserFound = false, isPasswordOk = false;

                    userReader = new Scanner(userFile);
                    while (userReader.hasNext()) {
                        userDataFromDB = userReader.nextLine().split("~");
                        usernameFromDB = userDataFromDB[0].split(":")[1];
                        userIdFromDB = userDataFromDB[1].split(":")[1];
                        userPasswordFromDB = userDataFromDB[2].split(":")[1];
                        if (usernameFromDB.equals(studentUsernameOrId) || userIdFromDB.equals(studentUsernameOrId)) {
                            isUserFound = true;
                            currentUserId = userIdFromDB;
                            System.out.println("current user id: " + currentUserId);
                            if (userPasswordFromDB.equals(studentPassword)) {
                                isPasswordOk = true;
                            }
                            break;
                        }
                    }

                    currentUserFile.createNewFile();
                    Writer userWriter = new FileWriter(currentUserFile);
                    userWriter.write(currentUserId);
                    userWriter.flush();
                    userWriter.close();

                    if (isUserFound) {
                        if (isPasswordOk) {
                            writer("found");
                        } else
                            writer("wrong password");
                        break;
                    } else {
                        writer("not found");
                    }
                    break;

                case "userinfo":

                    String name = "", lastname = "", id = "", unitCounts = "", totalAvg = "";

                    Scanner currentUserReader = new Scanner(currentUserFile);
                    currentUserId = currentUserReader.nextLine();
                    currentUserReader.close();
                    System.out.println("current user id: " + currentUserId);

                    studentReader = new Scanner(studentFile);
                    while (studentReader.hasNext()) {
                        studentDetailsFromDB = studentReader.nextLine().split(",");
                        if (studentDetailsFromDB[2].split(":")[1].equals(currentUserId)) {
                            name = studentDetailsFromDB[0].split(":")[1];
                            lastname = studentDetailsFromDB[1].split(":")[1];
                            id = studentDetailsFromDB[2].split(":")[1];
                            unitCounts = studentDetailsFromDB[4].split(":")[1];
                            totalAvg = studentDetailsFromDB[6].split(":")[1];
                            break;
                        }
                    }
                    writer(name + "~" + lastname + "~" + id + "~" + unitCounts + "~" + totalAvg);
                    break;

                case "password":
                    //password~pass
                    String newPassword = command.split("~")[1];
                    System.out.println("new password: " + newPassword);

                    currentUserReader = new Scanner(currentUserFile);
                    currentUserId = currentUserReader.nextLine();
                    currentUserReader.close();
                    System.out.println("current user id: " + currentUserId);

                    try {
                        userTempFile.createNewFile();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    Writer newWriter = new FileWriter(userTempFile, true);
                    userReader = new Scanner(userFile);
                    while (userReader.hasNext()) {
                        userDataFromDB = userReader.nextLine().split("~");
                        usernameFromDB = userDataFromDB[0].split(":")[1];
                        userIdFromDB = userDataFromDB[1].split(":")[1];
                        userPasswordFromDB = userDataFromDB[2].split(":")[1];
                        if (userIdFromDB.equals(currentUserId))
                            newWriter.write("username:" + usernameFromDB + "~id:" + userIdFromDB + "~password:" + newPassword + "\n");
                        else
                            newWriter.write("username:" + usernameFromDB + "~id:" + userIdFromDB + "~password:" + userPasswordFromDB + "\n");
                        newWriter.flush();

                    }
                    newWriter.close();
                    userFile.delete();
                    userTempFile.renameTo(new File(userPath));
                    writer("password changed");
                    break;

                case "delete":
                    currentUserReader = new Scanner(currentUserFile);
                    currentUserId = currentUserReader.nextLine();
                    currentUserReader.close();
                    System.out.println("current user id: " + currentUserId);

                    try {
                        userTempFile.createNewFile();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    newWriter = new FileWriter(userTempFile, true);
                    userReader = new Scanner(userFile);
                    while (userReader.hasNext()) {
                        userDataFromDB = userReader.nextLine().split("~");
                        usernameFromDB = userDataFromDB[0].split(":")[1];
                        userIdFromDB = userDataFromDB[1].split(":")[1];
                        userPasswordFromDB = userDataFromDB[2].split(":")[1];
                        if (!userIdFromDB.equals(currentUserId))
                            newWriter.write("username:" + usernameFromDB + "~id:" + userIdFromDB + "~password:" + userPasswordFromDB + "\n");
                        newWriter.flush();

                    }
                    newWriter.close();
                    userFile.delete();
                    userTempFile.renameTo(new File(userPath));
                    currentUserFile.delete();
                    writer("user deleted");
                    break;

                case "summary":
                    String bestMark = "", worstMark = "", exams = "", homeworks = "", pastDeadline = "";
                    String[] courses, marks;

                    int numOfExams, numOfAssignments = 0, numOfPastAssignments = 0;

                    LocalDate now = LocalDate.now();

                    currentUserReader = new Scanner(currentUserFile);
                    currentUserId = currentUserReader.nextLine();
                    currentUserReader.close();
                    System.out.println("current user id: " + currentUserId);

                    studentReader = new Scanner(studentFile);
                    while (studentReader.hasNext()) {
                        studentDetailsFromDB = studentReader.nextLine().split(",");
                        if (studentDetailsFromDB[2].split(":")[1].equals(currentUserId)) {
                            courses = studentDetailsFromDB[5].split(":")[1].substring(1, studentDetailsFromDB[5].split(":")[1].length() - 1).split("~");
                            numOfExams = courses.length;

                            for (String couId : courses) {
                                Scanner courseReader = new Scanner(courseFile);
                                while (courseReader.hasNext()) {
                                    String[] courseData = courseReader.nextLine().split(",");
                                    String courseId = courseData[1].split(":")[1];
                                    if (couId.equals(courseId)) {
                                        String[] assignmentIds = courseData[9].split(":")[1].substring(1, courseData[9].split(":")[1].length() - 1).split("~");
                                        for (String assignID : assignmentIds) {
                                            Scanner assignmentReader = new Scanner(assignmentFile);
                                            while (assignmentReader.hasNext()) {
                                                String[] assignmentData = assignmentReader.nextLine().split(",");
                                                String assignmentId = assignmentData[0].split(":")[1];
                                                if (assignmentId.equals(assignID)) {
                                                    LocalDate assignmentDeadline = LocalDate.parse(assignmentData[1].split(":")[1]);
                                                    if (assignmentDeadline.isAfter(now)) {
                                                        numOfAssignments++;
                                                    } else if (assignmentDeadline.isBefore(now)) {
                                                        numOfPastAssignments++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            marks = studentDetailsFromDB[7].split(":")[1].substring(1, studentDetailsFromDB[7].split(":")[1].length() - 1).split("~");
                            Map<String, Double> mapOfMarks = new HashMap<>();
                            for (String markDetails : marks) {
                                mapOfMarks.put(markDetails.split("=")[0], Double.valueOf(markDetails.split("=")[1]));
                            }
                            double maxMark = -1.0;
                            double minMark = 21.0;
                            for (String courseInMarks : mapOfMarks.keySet()) {
                                if (mapOfMarks.get(courseInMarks) > maxMark) {
                                    maxMark = mapOfMarks.get(courseInMarks);
                                }
                                if (mapOfMarks.get(courseInMarks) < minMark) {
                                    minMark = mapOfMarks.get(courseInMarks);
                                }
                            }
                            bestMark = String.valueOf(maxMark);
                            worstMark = String.valueOf(minMark);
                            exams = String.valueOf(numOfExams);
                            homeworks = String.valueOf(numOfAssignments);
                            pastDeadline = String.valueOf(numOfPastAssignments);
                            break;
                        }
                    }

                    writer(bestMark + "~" + worstMark + "~" + exams + "~" + homeworks + "~" + pastDeadline);
                    break;

                case "assignment":

                    currentUserReader = new Scanner(currentUserFile);
                    currentUserId = currentUserReader.nextLine();
                    currentUserReader.close();
                    System.out.println("current user id: " + currentUserId);


                    StringBuilder assignmentDetails = new StringBuilder();
                    studentReader = new Scanner(studentFile);
                    while (studentReader.hasNext()) {
                        studentDetailsFromDB = studentReader.nextLine().split(",");
                        if (studentDetailsFromDB[2].split(":")[1].equals(currentUserId)) {
                            courses = studentDetailsFromDB[5].split(":")[1].substring(1, studentDetailsFromDB[5].split(":")[1].length() - 1).split("~");
                            for (String couId : courses) {
                                Scanner courseReader = new Scanner(courseFile);
                                while (courseReader.hasNext()) {
                                    String[] courseData = courseReader.nextLine().split(",");
                                    String courseId = courseData[1].split(":")[1];
                                    if (couId.equals(courseId)) {
                                        String[] assignmentIds = courseData[9].split(":")[1].substring(1, courseData[9].split(":")[1].length() - 1).split("~");
                                        for (String assignID : assignmentIds) {
                                            Scanner assignmentReader = new Scanner(assignmentFile);
                                            while (assignmentReader.hasNext()) {
                                                String[] assignmentData = assignmentReader.nextLine().split(",");
                                                String assignmentId = assignmentData[0].split(":")[1];
                                                if (assignmentId.equals(assignID)) {
                                                    String assignmentName = assignmentData[4].split(":")[1];
                                                    String assignmentDeadline = assignmentData[1].split(":")[1];
                                                    assignmentDetails.append(assignmentName).append("~").append(assignmentDeadline).append("#");
                                                }
                                            }
                                            assignmentReader.close();
                                        }
                                    }
                                }
                                courseReader.close();
                            }
                            break;
                        }
                    }
                    studentReader.close();
                    writer(assignmentDetails.toString());
                    break;

                case "home assignment":

                    currentUserReader = new Scanner(currentUserFile);
                    currentUserId = currentUserReader.nextLine();
                    currentUserReader.close();
                    System.out.println("current user id: " + currentUserId);


                    assignmentDetails = new StringBuilder();
                    studentReader = new Scanner(studentFile);
                    while (studentReader.hasNext()) {
                        studentDetailsFromDB = studentReader.nextLine().split(",");
                        if (studentDetailsFromDB[2].split(":")[1].equals(currentUserId)) {
                            courses = studentDetailsFromDB[5].split(":")[1].substring(1, studentDetailsFromDB[5].split(":")[1].length() - 1).split("~");
                            for (String couId : courses) {
                                Scanner courseReader = new Scanner(courseFile);
                                while (courseReader.hasNext()) {
                                    String[] courseData = courseReader.nextLine().split(",");
                                    String courseId = courseData[1].split(":")[1];
                                    if (couId.equals(courseId)) {
                                        String[] assignmentIds = courseData[9].split(":")[1].substring(1, courseData[9].split(":")[1].length() - 1).split("~");
                                        for (String assignID : assignmentIds) {
                                            Scanner assignmentReader = new Scanner(assignmentFile);
                                            while (assignmentReader.hasNext()) {
                                                String[] assignmentData = assignmentReader.nextLine().split(",");
                                                String assignmentId = assignmentData[0].split(":")[1];
                                                if (assignmentId.equals(assignID)) {
                                                    String assignmentName = assignmentData[4].split(":")[1];
                                                    String assignmentDeadline = assignmentData[1].split(":")[1];
                                                    assignmentDetails.append(assignmentName).append("~").append(assignmentDeadline).append("#");
                                                }
                                            }
                                            assignmentReader.close();
                                        }
                                    }
                                }
                                courseReader.close();
                            }
                            break;
                        }
                    }
                    studentReader.close();
                    writer(assignmentDetails.toString());
                    break;

                case "classes":

                    currentUserReader = new Scanner(currentUserFile);
                    currentUserId = currentUserReader.nextLine();
                    currentUserReader.close();
                    System.out.println("current user id: " + currentUserId);


                    StringBuilder courseDetails = new StringBuilder();
                    studentReader = new Scanner(studentFile);
                    while (studentReader.hasNext()) {
                        studentDetailsFromDB = studentReader.nextLine().split(",");
                        if (studentDetailsFromDB[2].split(":")[1].equals(currentUserId)) {
                            courses = studentDetailsFromDB[5].split(":")[1].substring(1, studentDetailsFromDB[5].split(":")[1].length() - 1).split("~");
                            for (String couId : courses) {
                                Scanner courseReader = new Scanner(courseFile);
                                while (courseReader.hasNext()) {
                                    String[] courseData = courseReader.nextLine().split(",");
                                    String courseId = courseData[1].split(":")[1];
                                    if (couId.equals(courseId)) {
                                        String courseName = courseData[0].split(":")[1];
                                        String teacherId = courseData[2].split(":")[1];
                                        String teacherName = "";
                                        Scanner teacherReader = new Scanner(teacherFile);
                                        while (teacherReader.hasNext()) {
                                            String[] teacherData = teacherReader.nextLine().split(",");
                                            String teachId = teacherData[2].split(":")[1];
                                            if (teachId.equals(teacherId)) {
                                                teacherName = teacherData[0].split(":")[1] + " " + teacherData[1].split(":")[1];
                                            }
                                        }
                                        teacherReader.close();
                                        String units = courseData[4].split(":")[1];
                                        String assignments = courseData[8].split(":")[1];
                                        String[] marksOfCourse = courseData[3].split(":")[1].substring(1, courseData[3].split(":")[1].length() - 1).split("\\*");
                                        Map<String, Double> markCourse = new HashMap<>();
                                        for (String str : marksOfCourse) {
                                            markCourse.put(str.split("#")[0], Double.valueOf(str.split("#")[1]));
                                        }
                                        String topStudent = "";
                                        String topStudentId = "";
                                        double maxMark = -1.0;
                                        for (String str : markCourse.keySet()) {
                                            if (markCourse.get(str) > maxMark) {
                                                maxMark = markCourse.get(str);
                                                topStudentId = str;
                                            }
                                        }
                                        Scanner stuReader = new Scanner(studentFile);
                                        while (stuReader.hasNext()) {
                                            String[] studentRead = stuReader.nextLine().split(",");
                                            String stuId = studentRead[2].split(":")[1];
                                            if (stuId.equals(topStudentId)) {
                                                topStudent = studentRead[0].split(":")[1] + " " + studentRead[1].split(":")[1];
                                            }
                                        }
                                        stuReader.close();
                                        //courseName~teacherName~units~assignments~topStudent#
                                        courseDetails.append(courseName).append("~").append(teacherName).append("~").append(units).append("~").append(assignments).append("~").append(topStudent).append("#");
                                    }
                                }
                                courseReader.close();
                            }
                            break;
                        }
                    }
                    studentReader.close();
                    writer(courseDetails.toString());
                    break;

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


