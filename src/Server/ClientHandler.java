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

            //for windows, uncomment this part and comment the previous part.
        /*  String studentPath = filePath + "\\students_data.txt";
            String assignmentPath = filePath + "\\assignments_data.txt";
            String teacherPath = filePath + "\\teachers_data.txt";
            String coursePath = filePath + "\\courses_data.txt";
             String userPath = filePath + "\\users_data.txt"; */

            File studentFile = new File(studentPath);
            File assignmentFile = new File(assignmentPath);
            File teacherFile = new File(teacherPath);
            File courseFile = new File(coursePath);
            File userFile = new File(userPath);

            try {
                userFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            String[] userData;
            String studentUsername;
            String studentId;
            String studentPassword;
            String studentUsernameOrId = "";

            String studentDataFromDB;
            String[] studentDetailsFromDB;

            command = listener();

            String action = command.split("~")[0];
            switch (action) {

                case "signup":
                    userData = command.split("~");   //example: signup~testUser~123456789~password
                    studentUsername= userData[1];
                    studentId = userData[2];
                    studentPassword = userData[3];

                    boolean isUsernameDuplicate = false;
                    Scanner userReader = new Scanner(userFile);
                    while (userReader.hasNext()) {
                        String[] userDataFromDB = userReader.nextLine().split("~");
                        String username = userDataFromDB[0].split(":")[1];
                        if (username.equals(studentUsername)) {
                            isUsernameDuplicate = true;
                            break;
                        }
                    }

                    if (!isUsernameDuplicate) {
                        Scanner studentReader = new Scanner(studentFile);
                        boolean isStudentFound = false;
                        while (studentReader.hasNext()) {
                            studentDataFromDB = studentReader.nextLine();
                            studentDetailsFromDB = studentDataFromDB.split(",");
                            if (studentDetailsFromDB[2].split(":")[1].equals(studentId))
                                isStudentFound = true;
                        }
                        if (isStudentFound) {
                            writer("found");
                            Writer writer = new FileWriter(userFile, true);
                            writer.write("username:" + studentUsername+ "~id:" + studentId + "~password:" + studentPassword + "\n");
                            writer.flush();
                            writer.close();
                        } else
                            writer("not found");
                        break;
                    } else {
                        writer("duplicate");
                    }
                    break;

                case "login":
                    String[] studentData = command.split("~");   //example: login~testUser~password
                    studentUsernameOrId = studentData[1];
                    studentPassword = studentData[2];

                    boolean isUserFound = false;
                    boolean isPasswordOk = false;
                    userReader = new Scanner(userFile);
                    while (userReader.hasNext()) {
                        String[] userDataFromDB = userReader.nextLine().split("~");
                        String username = userDataFromDB[0].split(":")[1];
                        String id = userDataFromDB[1].split(":")[1];
                        String password = userDataFromDB[2].split(":")[1];
                        if (username.equals(studentUsernameOrId) || id.equals(studentUsernameOrId)) {
                            isUserFound = true;
                            if (password.equals(studentPassword)) {
                                isPasswordOk = true;
                            }
                            break;
                        }
                    }

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

                    //userinfo
                    String name = "", lastname = "", id = "", unitCounts = "", totalAvg = "";

                    userReader = new Scanner(userFile);
                    while (userReader.hasNext()) {
                        String[] userDataFromDB = userReader.nextLine().split("~");
                        String username = userDataFromDB[0].split(":")[1];
                        id = userDataFromDB[1].split(":")[1];
                        if (username.equals(studentUsernameOrId) || id.equals(studentUsernameOrId))
                            break;
                    }

                    Scanner studentReader = new Scanner(studentFile);
                    while (studentReader.hasNext()) {
                        studentDataFromDB = studentReader.nextLine();
                        studentDetailsFromDB = studentDataFromDB.split(",");
                        if (studentDetailsFromDB[2].split(":")[1].equals(id)) {
                            name = studentDetailsFromDB[0].split(":")[1];
                            lastname = studentDetailsFromDB[1].split(":")[1];
                            id = studentDetailsFromDB[2].split(":")[1];
                            unitCounts = studentDetailsFromDB[4].split(":")[1];
                            totalAvg = studentDetailsFromDB[6].split(":")[1];
                            break;
                        }
                    }
                    //name~lastname~id~unitCount~avg
                    writer(name + "~" + lastname + "~" + id + "~" + unitCounts + "~" + totalAvg);

                    break;


            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
