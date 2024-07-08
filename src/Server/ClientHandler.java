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
            StringBuilder sb = new StringBuilder();
            int index = dis.read();
            while (index != 0) {
                sb.append((char) index);
                index = dis.read();
            }
            Scanner s = new Scanner(sb.toString());
            StringBuilder request = new StringBuilder();
            while (s.hasNextLine()) {
                request.append(s.nextLine());
            }
            System.out.println("listener has read command successfully: { " + request.toString() + " }");
            return request.toString();
        } catch (IOException e) {
            System.out.println("Error in listener : " + e.getMessage());
        }
        return "Error!";
    }

    public void writer(String write) throws IOException {
        try{
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
            command = listener();
            String[] studentData = command.split("~");
            for (String s : studentData)
                System.out.println(s);

            String studentUsername = studentData[0];
            String studentId = studentData[1];
            String studentPassword = studentData[2];

            //database directory
            String filePath = "./Database";
            //String filePath = ".\\Database"; //for windows, uncomment this and comment the previous line.
            File tempFile = new File(filePath);
            tempFile.mkdir();

            String studentPath = filePath + "/students_data.txt";
            String assignmentPath = filePath + "/assignments_data.txt";
            String teacherPath = filePath + "/teachers_data.txt";
            String coursePath = filePath + "/courses_data.txt";
            String userPath = filePath + "/users_data.txt";

            //for windows, uncomment this part and comment the previous part.
        /*  String studentPath = filePath + "\\students_data.txt";
            String assignmentPath = filePath + "\\assignments_data.txt";
            String teacherPath = filePath + "\\teachers_data.txt";
            String coursePath = filePath + "\\courses_data.txt"; */

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

            Scanner reader = new Scanner(studentFile);
            String studentDataFromDB = "";
            boolean isStudentFound = false;
            while (reader.hasNext()) {
                studentDataFromDB = reader.nextLine();
                String[] detail = studentDataFromDB.split(",");
                if (detail[2].split(":")[1].equals(studentId))
                    isStudentFound = true;
            }
            if (isStudentFound) {
                writer("found");
                Writer writer = new FileWriter(userFile);
                writer.write("username:" + studentUsername + "~id:" + studentId + "~password:" + studentPassword + "\n");
                writer.flush();
                writer.close();
            }
            else
                writer("not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
