package Server;

import java.io.*;
import java.net.*;
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
            System.out.println("Writer started");
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
            String studentUsername, studentId, studentPassword, studentUsernameOrId = "";

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
                                studentReader.close();
                            }
                        }
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

            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
