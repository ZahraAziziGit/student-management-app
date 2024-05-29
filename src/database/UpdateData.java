package database;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class UpdateData {
    public static void updateStudentData(File source, File temp, String id) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] studentData = reader.nextLine().split(",");
                String[] firstNameFromDatabase = studentData[0].split(":");
                String[] lastNameFromDatabase = studentData[1].split(":");
                String[] idFromDatabase = studentData[2].split(":");
                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeStudent(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            source.createNewFile();
            reader = new Scanner(temp);
            while (reader.hasNext()) {
                String[] studentData = reader.nextLine().split(",");
                String[] firstNameFromDatabase = studentData[0].split(":");
                String[] lastNameFromDatabase = studentData[1].split(":");
                String[] idFromDatabase = studentData[2].split(":");
                StoreData.storeStudent(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1], source);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateAssignmentData(File source, File temp, String id) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] assignmentData = reader.nextLine().split(",");
                String[] idFromDatabase = assignmentData[0].split(":");
                String[] deadlineFromDatabase = assignmentData[1].split(":");
                String[] isActiveFromDatabase = assignmentData[2].split(":");
                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), temp);
            }
            reader.close();
            source.delete();
            source.createNewFile();
            reader = new Scanner(temp);
            while (reader.hasNext()) {
                String[] assignmentData = reader.nextLine().split(",");
                String[] idFromDatabase = assignmentData[0].split(":");
                String[] deadlineFromDatabase = assignmentData[1].split(":");
                String[] isActiveFromDatabase = assignmentData[2].split(":");
                StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                        Objects.equals(isActiveFromDatabase[1], "true"), source);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateAssignmentData(File source, File temp, String id, String deadline) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] assignmentData = reader.nextLine().split(",");
                String[] idFromDatabase = assignmentData[0].split(":");
                String[] deadlineFromDatabase = assignmentData[1].split(":");
                String[] isActiveFromDatabase = assignmentData[2].split(":");
                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), temp);
                else
                    StoreData.storeAssignment(idFromDatabase[1], deadline,
                            Objects.equals(isActiveFromDatabase[1], "true"), temp);
            }
            reader.close();
            source.delete();
            source.createNewFile();
            reader = new Scanner(temp);
            while (reader.hasNext()) {
                String[] assignmentData = reader.nextLine().split(",");
                String[] idFromDatabase = assignmentData[0].split(":");
                String[] deadlineFromDatabase = assignmentData[1].split(":");
                String[] isActiveFromDatabase = assignmentData[2].split(":");
                StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                        Objects.equals(isActiveFromDatabase[1], "true"), source);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateTeacherData(File source, File temp, String id) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] teacherData = reader.nextLine().split(",");
                String[] firstNameFromDatabase = teacherData[0].split(":");
                String[] lastNameFromDatabase = teacherData[1].split(":");
                String[] idFromDatabase = teacherData[2].split(":");
                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeTeacher(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            source.createNewFile();
            reader = new Scanner(temp);
            while (reader.hasNext()) {
                String[] teacherData = reader.nextLine().split(",");
                String[] firstNameFromDatabase = teacherData[0].split(":");
                String[] lastNameFromDatabase = teacherData[1].split(":");
                String[] idFromDatabase = teacherData[2].split(":");
                StoreData.storeTeacher(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1], source);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateCourseData(File source, File temp, String id) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");
                String[] courseNameFromDatabase = courseData[0].split(":");
                String[] idFromDatabase = courseData[1].split(":");
                String[] teacherDataFromDatabase = courseData[2].split(":");
                String[] teacherDetailedData = teacherDataFromDatabase[1].split("~");
                String[] teacherName = teacherDetailedData[0].split("=");
                String[] teacherLastName = teacherDetailedData[1].split("=");
                String[] teacherId = teacherDetailedData[2].split("=");
                String[] unitsFromDatabase = courseData[3].split(":");
                String[] examDateFromDatabase = courseData[4].split(":");
                String[] isActiveFromDatabase = courseData[5].split(":");
                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1], teacherName[1], teacherLastName[1],
                            teacherId[1], unitsFromDatabase[1], examDateFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), temp);
            }
            reader.close();
            source.delete();
            source.createNewFile();
            reader = new Scanner(temp);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");
                String[] courseNameFromDatabase = courseData[0].split(":");
                String[] idFromDatabase = courseData[1].split(":");
                String[] teacherDataFromDatabase = courseData[2].split(":");
                String[] teacherDetailedData = teacherDataFromDatabase[1].split("~");
                String[] teacherName = teacherDetailedData[0].split("=");
                String[] teacherLastName = teacherDetailedData[1].split("=");
                String[] teacherId = teacherDetailedData[2].split("=");
                String[] unitsFromDatabase = courseData[3].split(":");
                String[] examDateFromDatabase = courseData[4].split(":");
                String[] isActiveFromDatabase = courseData[5].split(":");
                StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1], teacherName[1], teacherLastName[1],
                        teacherId[1], unitsFromDatabase[1], examDateFromDatabase[1],
                        Objects.equals(isActiveFromDatabase[1], "true"), source);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

