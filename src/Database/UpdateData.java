package Database;

import java.io.*;
import java.util.*;

public class UpdateData {
    //remove student
    public static void updateStudentData(File source, File temp, String id) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] studentData = reader.nextLine().split(",");

                String[] firstNameFromDatabase = studentData[0].split(":");
                String[] lastNameFromDatabase = studentData[1].split(":");
                String[] idFromDatabase = studentData[2].split(":");
                String[] numOfCoursesFromDatabase = studentData[3].split(":");
                String[] numOfUnitsFromDatabase = studentData[4].split(":");
                String[] coursesFromDatabase = studentData[5].split(":");
                String[] totalAvgFromDatabase = studentData[6].split(":");
                String[] marksFromDatabase = studentData[7].split(":");

                List<String> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    Collections.addAll(listOfCoursesFromDatabase, coursesIds);
                }

                Map<String, Double> listOfMarksFromDatabase = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    String[] marksMap = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("~");
                    for (String data : marksMap) {
                        listOfMarksFromDatabase.put(data.split("=")[0], Double.parseDouble(data.split("=")[1]));
                    }
                }

                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeStudent(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                            Integer.parseInt(numOfCoursesFromDatabase[1]), Integer.parseInt(numOfUnitsFromDatabase[1]),
                            listOfCoursesFromDatabase, Double.parseDouble(totalAvgFromDatabase[1]),
                            listOfMarksFromDatabase, temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update student's courses list
    public static void updateStudentData(File source, File temp, String id, int numOfCourses, int numOfUnits, List<String> listOfCourse) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] studentData = reader.nextLine().split(",");

                String[] firstNameFromDatabase = studentData[0].split(":");
                String[] lastNameFromDatabase = studentData[1].split(":");
                String[] idFromDatabase = studentData[2].split(":");
                String[] numOfCoursesFromDatabase = studentData[3].split(":");
                String[] numOfUnitsFromDatabase = studentData[4].split(":");
                String[] coursesFromDatabase = studentData[5].split(":");
                String[] totalAvgFromDatabase = studentData[6].split(":");
                String[] marksFromDatabase = studentData[7].split(":");

                List<String> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    listOfCoursesFromDatabase.addAll(Arrays.asList(coursesIds));
                }

                Map<String, Double> listOfMarksFromDatabase = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    String[] marksMap = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("~");
                    for (String data : marksMap) {
                        listOfMarksFromDatabase.put(data.split("=")[0], Double.parseDouble(data.split("=")[1]));
                    }
                }

                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeStudent(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                            Integer.parseInt(numOfCoursesFromDatabase[1]), Integer.parseInt(numOfUnitsFromDatabase[1]),
                            listOfCoursesFromDatabase, Double.parseDouble(totalAvgFromDatabase[1]),
                            listOfMarksFromDatabase, temp);
                else
                    StoreData.storeStudent(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                            numOfCourses, numOfUnits,
                            listOfCourse, Double.parseDouble(totalAvgFromDatabase[1]),
                            listOfMarksFromDatabase, temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update student's marks
    public static void updateStudentData(File source, File temp, String id, double totalAvg, Map<String, Double> marks) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] studentData = reader.nextLine().split(",");

                String[] firstNameFromDatabase = studentData[0].split(":");
                String[] lastNameFromDatabase = studentData[1].split(":");
                String[] idFromDatabase = studentData[2].split(":");
                String[] numOfCoursesFromDatabase = studentData[3].split(":");
                String[] numOfUnitsFromDatabase = studentData[4].split(":");
                String[] coursesFromDatabase = studentData[5].split(":");
                String[] totalAvgFromDatabase = studentData[6].split(":");
                String[] marksFromDatabase = studentData[7].split(":");

                List<String> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    Collections.addAll(listOfCoursesFromDatabase, coursesIds);
                }

                Map<String, Double> listOfMarksFromDatabase = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    String[] marksMap = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("~");
                    for (String data : marksMap) {
                        listOfMarksFromDatabase.put(data.split("=")[0], Double.parseDouble(data.split("=")[1]));
                    }
                }

                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeStudent(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                            Integer.parseInt(numOfCoursesFromDatabase[1]), Integer.parseInt(numOfUnitsFromDatabase[1]),
                            listOfCoursesFromDatabase, Double.parseDouble(totalAvgFromDatabase[1]),
                            listOfMarksFromDatabase, temp);
                else
                    StoreData.storeStudent(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                            Integer.parseInt(numOfCoursesFromDatabase[1]), Integer.parseInt(numOfUnitsFromDatabase[1]),
                            listOfCoursesFromDatabase, totalAvg,
                            marks, temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    //remove assignment
    public static void updateAssignmentData(File source, File temp, String id) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] assignmentData = reader.nextLine().split(",");
                String[] idFromDatabase = assignmentData[0].split(":");
                String[] deadlineFromDatabase = assignmentData[1].split(":");
                String[] isActiveFromDatabase = assignmentData[2].split(":");
                String[] courseFromDatabase = assignmentData[3].split(":");
                String[] nameFromDatabase = assignmentData[4].split(":");
                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], nameFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update assignment's deadline
    public static void updateAssignmentData(File source, File temp, String id, String deadline) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] assignmentData = reader.nextLine().split(",");
                String[] idFromDatabase = assignmentData[0].split(":");
                String[] deadlineFromDatabase = assignmentData[1].split(":");
                String[] isActiveFromDatabase = assignmentData[2].split(":");
                String[] courseFromDatabase = assignmentData[3].split(":");
                String[] nameFromDatabase = assignmentData[4].split(":");
                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], nameFromDatabase[1], temp);
                else
                    StoreData.storeAssignment(idFromDatabase[1], deadline,
                            Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], nameFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update assignment's deadline
    public static void updateAssignmentCourse(File source, File temp, String id, String courseId) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] assignmentData = reader.nextLine().split(",");
                String[] idFromDatabase = assignmentData[0].split(":");
                String[] deadlineFromDatabase = assignmentData[1].split(":");
                String[] isActiveFromDatabase = assignmentData[2].split(":");
                String[] courseFromDatabase = assignmentData[3].split(":");
                String[] nameFromDatabase = assignmentData[4].split(":");

                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], nameFromDatabase[1], temp);
                else
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), courseId, nameFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //change assignment status
    public static void updateAssignmentData(File source, File temp, String id, boolean active) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] assignmentData = reader.nextLine().split(",");
                String[] idFromDatabase = assignmentData[0].split(":");
                String[] deadlineFromDatabase = assignmentData[1].split(":");
                String[] isActiveFromDatabase = assignmentData[2].split(":");
                String[] courseFromDatabase = assignmentData[3].split(":");
                String[] nameFromDatabase = assignmentData[4].split(":");

                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], nameFromDatabase[1], temp);
                else
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            active, courseFromDatabase[1], nameFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    //remove teacher
    public static void updateTeacherData(File source, File temp, String id) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] teacherData = reader.nextLine().split(",");

                String[] firstNameFromDatabase = teacherData[0].split(":");
                String[] lastNameFromDatabase = teacherData[1].split(":");
                String[] idFromDatabase = teacherData[2].split(":");
                String[] numOfCoursesFromDatabase = teacherData[3].split(":");
                String[] coursesFromDatabase = teacherData[4].split(":");

                List<String> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    listOfCoursesFromDatabase.addAll(Arrays.asList(coursesIds));
                }

                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeTeacher(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                            Integer.parseInt(numOfCoursesFromDatabase[1]), listOfCoursesFromDatabase, temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update teacher's course list
    public static void updateTeacherData(File source, File temp, String id, int numOfCourses, List<String> listOfCourse) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] teacherData = reader.nextLine().split(",");

                String[] firstNameFromDatabase = teacherData[0].split(":");
                String[] lastNameFromDatabase = teacherData[1].split(":");
                String[] idFromDatabase = teacherData[2].split(":");
                String[] numOfCoursesFromDatabase = teacherData[3].split(":");
                String[] coursesFromDatabase = teacherData[4].split(":");

                List<String> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    Collections.addAll(listOfCoursesFromDatabase, coursesIds);
                }

                if (Objects.equals(idFromDatabase[1], id))
                    StoreData.storeTeacher(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                            numOfCourses, listOfCourse, temp);
                else
                    StoreData.storeTeacher(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                            Integer.parseInt(numOfCoursesFromDatabase[1]), listOfCoursesFromDatabase, temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    //remove course
    public static void updateCourseData(File source, File temp, String id) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");

                String[] courseNameFromDatabase = courseData[0].split(":");
                String[] idFromDatabase = courseData[1].split(":");
                String[] teacherIdFromDatabase = courseData[2].split(":");
                String[] marksFromDatabase = courseData[3].split(":");
                String[] unitsFromDatabase = courseData[4].split(":");
                String[] numOfStudentsFromDatabase = courseData[5].split(":");
                String[] studentsFromDatabase = courseData[6].split(":");
                String[] isActiveFromDatabase = courseData[7].split(":");
                String[] numOfAssignmentsFromDatabase = courseData[8].split(":");
                String[] assignmentsFromDatabase = courseData[9].split(":");
                String[] examDateFromDatabase = courseData[10].split(":");


                List<String> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    listOfStudents.addAll(Arrays.asList(studentsIds));
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<String, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                            marksList.put(stuId, score);
                    }
                }

                List<String> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    listOfAssignments.addAll(Arrays.asList(assignmentsIds));
                }

                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marksList,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDateFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //change course status
    public static void updateCourseData(File source, File temp, String id, boolean active) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");

                String[] courseNameFromDatabase = courseData[0].split(":");
                String[] idFromDatabase = courseData[1].split(":");
                String[] teacherIdFromDatabase = courseData[2].split(":");
                String[] marksFromDatabase = courseData[3].split(":");
                String[] unitsFromDatabase = courseData[4].split(":");
                String[] numOfStudentsFromDatabase = courseData[5].split(":");
                String[] studentsFromDatabase = courseData[6].split(":");
                String[] isActiveFromDatabase = courseData[7].split(":");
                String[] numOfAssignmentsFromDatabase = courseData[8].split(":");
                String[] assignmentsFromDatabase = courseData[9].split(":");
                String[] examDateFromDatabase = courseData[10].split(":");


                List<String> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    listOfStudents.addAll(Arrays.asList(studentsIds));
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<String, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        marksList.put(stuId, score);
                    }
                }


                List<String> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    listOfAssignments.addAll(Arrays.asList(assignmentsIds));
                }


                if (Objects.equals(idFromDatabase[1], id))
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marksList,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            active,
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDateFromDatabase[1], temp);
                else
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marksList,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDateFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update course's students list
    public static void updateCourseData(File source, File temp, String id, List<String> newListOfStudents, int numOfStudents) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");

                String[] courseNameFromDatabase = courseData[0].split(":");
                String[] idFromDatabase = courseData[1].split(":");
                String[] teacherIdFromDatabase = courseData[2].split(":");
                String[] marksFromDatabase = courseData[3].split(":");
                String[] unitsFromDatabase = courseData[4].split(":");
                String[] numOfStudentsFromDatabase = courseData[5].split(":");
                String[] studentsFromDatabase = courseData[6].split(":");
                String[] isActiveFromDatabase = courseData[7].split(":");
                String[] numOfAssignmentsFromDatabase = courseData[8].split(":");
                String[] assignmentsFromDatabase = courseData[9].split(":");
                String[] examDateFromDatabase = courseData[10].split(":");

                List<String> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    listOfStudents.addAll(Arrays.asList(studentsIds));
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<String, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        marksList.put(stuId, score);
                    }
                }

                List<String> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    listOfAssignments.addAll(Arrays.asList(assignmentsIds));
                }

                if (Objects.equals(idFromDatabase[1], id))
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marksList,
                            Integer.parseInt(unitsFromDatabase[1]), numOfStudents, newListOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDateFromDatabase[1], temp);
                else
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marksList,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDateFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update course's assignments list
    public static void updateCourseData(File source, File temp, String id, int numOfAssignments, List<String> newListOfAssignments) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");

                String[] courseNameFromDatabase = courseData[0].split(":");
                String[] idFromDatabase = courseData[1].split(":");
                String[] teacherIdFromDatabase = courseData[2].split(":");
                String[] marksFromDatabase = courseData[3].split(":");
                String[] unitsFromDatabase = courseData[4].split(":");
                String[] numOfStudentsFromDatabase = courseData[5].split(":");
                String[] studentsFromDatabase = courseData[6].split(":");
                String[] isActiveFromDatabase = courseData[7].split(":");
                String[] numOfAssignmentsFromDatabase = courseData[8].split(":");
                String[] assignmentsFromDatabase = courseData[9].split(":");
                String[] examDateFromDatabase = courseData[10].split(":");


                List<String> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    listOfStudents.addAll(Arrays.asList(studentsIds));
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<String, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        marksList.put(stuId, score);
                    }
                }

                List<String> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    listOfAssignments.addAll(Arrays.asList(assignmentsIds));
                }


                if (Objects.equals(idFromDatabase[1], id))
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marksList,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            numOfAssignments, newListOfAssignments,
                            examDateFromDatabase[1], temp);
                else
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marksList,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDateFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update course's teacher
    public static void updateCourseData(File source, String id, String teacher, File temp) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");

                String[] courseNameFromDatabase = courseData[0].split(":");
                String[] idFromDatabase = courseData[1].split(":");
                String[] teacherIdFromDatabase = courseData[2].split(":");
                String[] marksFromDatabase = courseData[3].split(":");
                String[] unitsFromDatabase = courseData[4].split(":");
                String[] numOfStudentsFromDatabase = courseData[5].split(":");
                String[] studentsFromDatabase = courseData[6].split(":");
                String[] isActiveFromDatabase = courseData[7].split(":");
                String[] numOfAssignmentsFromDatabase = courseData[8].split(":");
                String[] assignmentsFromDatabase = courseData[9].split(":");
                String[] examDateFromDatabase = courseData[10].split(":");


                List<String> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    listOfStudents.addAll(Arrays.asList(studentsIds));
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<String, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        marksList.put(stuId, score);
                    }
                }

                List<String> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    listOfAssignments.addAll(Arrays.asList(assignmentsIds));
                }

                if (Objects.equals(idFromDatabase[1], id))
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacher, marksList,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDateFromDatabase[1], temp);
                else
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marksList,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDateFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update course's marks list
    public static void updateCourseData(File source, File temp, String id, Map<String, Double> marks) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");

                String[] courseNameFromDatabase = courseData[0].split(":");
                String[] idFromDatabase = courseData[1].split(":");
                String[] teacherIdFromDatabase = courseData[2].split(":");
                String[] marksFromDatabase = courseData[3].split(":");
                String[] unitsFromDatabase = courseData[4].split(":");
                String[] numOfStudentsFromDatabase = courseData[5].split(":");
                String[] studentsFromDatabase = courseData[6].split(":");
                String[] isActiveFromDatabase = courseData[7].split(":");
                String[] numOfAssignmentsFromDatabase = courseData[8].split(":");
                String[] assignmentsFromDatabase = courseData[9].split(":");
                String[] examDateFromDatabase = courseData[10].split(":");


                List<String> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    listOfStudents.addAll(Arrays.asList(studentsIds));
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<String, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        marksList.put(stuId, score);
                    }
                }

                List<String> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    listOfAssignments.addAll(Arrays.asList(assignmentsIds));
                }

                if (Objects.equals(idFromDatabase[1], id))
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marks,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDateFromDatabase[1], temp);
                else
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marksList,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDateFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //change course's exam date
    public static void updateCourseData(File source, File temp, String id, String examDate) {
        try {
            temp.createNewFile();
            Scanner reader = new Scanner(source);
            while (reader.hasNext()) {
                String[] courseData = reader.nextLine().split(",");

                String[] courseNameFromDatabase = courseData[0].split(":");
                String[] idFromDatabase = courseData[1].split(":");
                String[] teacherIdFromDatabase = courseData[2].split(":");
                String[] marksFromDatabase = courseData[3].split(":");
                String[] unitsFromDatabase = courseData[4].split(":");
                String[] numOfStudentsFromDatabase = courseData[5].split(":");
                String[] studentsFromDatabase = courseData[6].split(":");
                String[] isActiveFromDatabase = courseData[7].split(":");
                String[] numOfAssignmentsFromDatabase = courseData[8].split(":");
                String[] assignmentsFromDatabase = courseData[9].split(":");
                String[] examDateFromDatabase = courseData[10].split(":");

                List<String> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    listOfStudents.addAll(Arrays.asList(studentsIds));
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<String, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        marksList.put(stuId, score);
                    }
                }


                List<String> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    listOfAssignments.addAll(Arrays.asList(assignmentsIds));
                }

                if (Objects.equals(idFromDatabase[1], id))
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marksList,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDate, temp);
                else
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacherIdFromDatabase[1], marksList,
                            Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                            Objects.equals(isActiveFromDatabase[1], "true"),
                            Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                            examDateFromDatabase[1], temp);
            }
            reader.close();
            source.delete();
            temp.renameTo(source);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

