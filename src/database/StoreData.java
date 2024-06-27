package database;

import classes.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StoreData {
    public static void storeStudent(String name, String lastName, String id, int numOfCourses,
                                    int numOfUnits, List<Course> listOfCourse, double totalAvg,
                                    double currentAvg, Map<Course, Double> marks, File file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("name:" + name + ",lastname:" + lastName + ",id:" + id +
                    ",numOfCourses:" + numOfCourses + ",numOfUnits:" + numOfUnits +
                    ",courses:{");
            StringBuilder coursesId = new StringBuilder();
            for (Course course : listOfCourse) {
                coursesId.append(course.getCourseID()).append("~");
            }
            if (!coursesId.isEmpty())
                writer.write(coursesId.substring(0, coursesId.length() - 1) + "}");
            else
                writer.write("}");
            writer.write(",totalAvg:" + totalAvg + ",currAvg:" + currentAvg + ",marks:{");
            StringBuilder marksStr = new StringBuilder();
            Iterator<Course> itr = marks.keySet().iterator();
            while(itr.hasNext()) {
                marksStr.append(itr.next().getCourseID()).append("=").append(marks.get(itr.next())).append("~");
            }
            if (!marksStr.isEmpty())
                writer.write(marksStr.substring(0, marksStr.length() - 1) + "}\n");
            else
                writer.write("}\n");
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
                    + "~id=" + teacherId + ",units:" + units + ",examDate:" + examDate + ",active:" + isActive + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


