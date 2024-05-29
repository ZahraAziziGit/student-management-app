package database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StoreData {
    public static void storeStudent(String name, String lastName, String id, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("name:" + name + ",lastname:" + lastName + ",id:" + id + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeAssignment(String id, String deadline, boolean isActive, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("id:" + id + ",deadline:" + deadline + ",active:" + isActive + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeTeacher(String name, String lastName, String id, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("name:" + name + ",lastname:" + lastName + ",id:" + id + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeCourse(String name, String id, String teacherName, String teacherLastName, String teacherId,
                                   String units, String examDate, boolean isActive, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("name:" + name + ",id:" + id + ",teacher:name=" + teacherName + "~lastname=" + teacherLastName
                    + "~id=" + teacherId  + ",units:" + units + ",examDate:" + examDate + ",active:" + isActive + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


