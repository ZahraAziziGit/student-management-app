package Database;

import Exceptions.NotFoundException;
import Classes.*;

import java.io.*;
import java.time.LocalDate;
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

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                List<Course> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCoursesFromDatabase.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                Map<Course, Double> listOfMarksFromDatabase = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    String[] marksMap = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("~");
                    for (String data : marksMap) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(data.split("=")[0], courseFile);
                            listOfMarksFromDatabase.put(tempCourse, Double.parseDouble(data.split("=")[1]));
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
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
            source.createNewFile();
            reader = new Scanner(temp);
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

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                List<Course> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCoursesFromDatabase.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                Map<Course, Double> listOfMarksFromDatabase = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    String[] marksMap = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("~");
                    for (String data : marksMap) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(data.split("=")[0], courseFile);
                            listOfMarksFromDatabase.put(tempCourse, Double.parseDouble(data.split("=")[1]));
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeStudent(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                        Integer.parseInt(numOfCoursesFromDatabase[1]), Integer.parseInt(numOfUnitsFromDatabase[1]),
                        listOfCoursesFromDatabase, Double.parseDouble(totalAvgFromDatabase[1]),
                        listOfMarksFromDatabase, source);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update student's courses list
    public static void updateStudentData(File source, File temp, String id, int numOfCourses, int numOfUnits, List<Course> listOfCourse) {
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

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                List<Course> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCoursesFromDatabase.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                Map<Course, Double> listOfMarksFromDatabase = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    String[] marksMap = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("~");
                    for (String data : marksMap) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(data.split("=")[0], courseFile);
                            listOfMarksFromDatabase.put(tempCourse, Double.parseDouble(data.split("=")[1]));
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
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
            source.createNewFile();
            reader = new Scanner(temp);
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

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                List<Course> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCoursesFromDatabase.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                Map<Course, Double> listOfMarksFromDatabase = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    String[] marksMap = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("~");
                    for (String data : marksMap) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(data.split("=")[0], courseFile);
                            listOfMarksFromDatabase.put(tempCourse, Double.parseDouble(data.split("=")[1]));
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeStudent(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                        Integer.parseInt(numOfCoursesFromDatabase[1]), Integer.parseInt(numOfUnitsFromDatabase[1]),
                        listOfCoursesFromDatabase, Double.parseDouble(totalAvgFromDatabase[1]),
                        listOfMarksFromDatabase, source);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update student's marks
    public static void updateStudentData(File source, File temp, String id, double totalAvg, Map<Course, Double> marks) {
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

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                List<Course> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCoursesFromDatabase.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                Map<Course, Double> listOfMarksFromDatabase = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    String[] marksMap = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("~");
                    for (String data : marksMap) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(data.split("=")[0], courseFile);
                            listOfMarksFromDatabase.put(tempCourse, Double.parseDouble(data.split("=")[1]));
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
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
            source.createNewFile();
            reader = new Scanner(temp);
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

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                List<Course> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCoursesFromDatabase.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                Map<Course, Double> listOfMarksFromDatabase = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    String[] marksMap = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("~");
                    for (String data : marksMap) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(data.split("=")[0], courseFile);
                            listOfMarksFromDatabase.put(tempCourse, Double.parseDouble(data.split("=")[1]));
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeStudent(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                        Integer.parseInt(numOfCoursesFromDatabase[1]), Integer.parseInt(numOfUnitsFromDatabase[1]),
                        listOfCoursesFromDatabase, Double.parseDouble(totalAvgFromDatabase[1]),
                        listOfMarksFromDatabase, source);
            }
            reader.close();
            temp.delete();
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
                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], temp);
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
                String[] courseFromDatabase = assignmentData[3].split(":");
                StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                        Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], source);
            }
            reader.close();
            temp.delete();
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
                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], temp);
                else
                    StoreData.storeAssignment(idFromDatabase[1], deadline,
                            Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], temp);
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
                String[] courseFromDatabase = assignmentData[3].split(":");
                StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                        Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], source);
            }
            reader.close();
            temp.delete();
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
                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], temp);
                else
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), courseId, temp);
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
                String[] courseFromDatabase = assignmentData[3].split(":");
                StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                        Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], source);
            }
            reader.close();
            temp.delete();
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
                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], temp);
                else
                    StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                            active, courseFromDatabase[1], temp);
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
                String[] courseFromDatabase = assignmentData[3].split(":");
                StoreData.storeAssignment(idFromDatabase[1], deadlineFromDatabase[1],
                        Objects.equals(isActiveFromDatabase[1], "true"), courseFromDatabase[1], source);
            }
            reader.close();
            temp.delete();
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

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                List<Course> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCoursesFromDatabase.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                if (!Objects.equals(idFromDatabase[1], id))
                    StoreData.storeTeacher(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                            Integer.parseInt(numOfCoursesFromDatabase[1]), listOfCoursesFromDatabase, temp);
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
                String[] numOfCoursesFromDatabase = teacherData[3].split(":");
                String[] coursesFromDatabase = teacherData[4].split(":");

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                List<Course> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCoursesFromDatabase.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeTeacher(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                        Integer.parseInt(numOfCoursesFromDatabase[1]), listOfCoursesFromDatabase, source);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update teacher's course list
    public static void updateTeacherData(File source, File temp, String id, int numOfCourses, List<Course> listOfCourse) {
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

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                List<Course> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCoursesFromDatabase.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
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
            source.createNewFile();
            reader = new Scanner(temp);
            while (reader.hasNext()) {
                String[] teacherData = reader.nextLine().split(",");
                String[] firstNameFromDatabase = teacherData[0].split(":");
                String[] lastNameFromDatabase = teacherData[1].split(":");
                String[] idFromDatabase = teacherData[2].split(":");
                String[] numOfCoursesFromDatabase = teacherData[3].split(":");
                String[] coursesFromDatabase = teacherData[4].split(":");

                String coursePath = "./Database/courses_data.txt";
                //String coursePath = ".\\Database\\courses_data.txt"; //uncomment this for windows
                File courseFile = new File(coursePath);

                List<Course> listOfCoursesFromDatabase = new ArrayList<>();
                if (coursesFromDatabase[1].length() > 2) {
                    String[] coursesIds = coursesFromDatabase[1].substring(1, coursesFromDatabase[1].length() - 1).split("~");
                    for (String courseId : coursesIds) {
                        try {
                            Course tempCourse = IdFinder.findCourseByID(courseId, courseFile);
                            listOfCoursesFromDatabase.add(tempCourse);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeTeacher(firstNameFromDatabase[1], lastNameFromDatabase[1], idFromDatabase[1],
                        Integer.parseInt(numOfCoursesFromDatabase[1]), listOfCoursesFromDatabase, source);
            }
            reader.close();
            temp.delete();
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
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
            source.createNewFile();
            reader = new Scanner(temp);
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                        teacherIdFromDatabase[1], marksList,
                        Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                        Objects.equals(isActiveFromDatabase[1], "true"),
                        Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                        examDateFromDatabase[1], temp);
            }
            reader.close();
            temp.delete();
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
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
            source.createNewFile();
            reader = new Scanner(temp);
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                        teacherIdFromDatabase[1], marksList,
                        Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                        Objects.equals(isActiveFromDatabase[1], "true"),
                        Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                        examDateFromDatabase[1], temp);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update course's students list
    public static void updateCourseData(File source, File temp, String id, List<Student> newListOfStudents, int numOfStudents) {
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
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
            source.createNewFile();
            reader = new Scanner(temp);
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                        teacherIdFromDatabase[1], marksList,
                        Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                        Objects.equals(isActiveFromDatabase[1], "true"),
                        Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                        examDateFromDatabase[1], temp);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update course's assignments list
    public static void updateCourseData(File source, File temp, String id, int numOfAssignments, List<Assignment> newListOfAssignments) {
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
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
            source.createNewFile();
            reader = new Scanner(temp);
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                        teacherIdFromDatabase[1], marksList,
                        Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                        Objects.equals(isActiveFromDatabase[1], "true"),
                        Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                        examDateFromDatabase[1], temp);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update course's teacher
    public static void updateCourseData(File source, File temp, String id, Teacher teacher) {
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                if (Objects.equals(idFromDatabase[1], id))
                    StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                            teacher.getTeacherID(), marksList,
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
            source.createNewFile();
            reader = new Scanner(temp);
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                        teacherIdFromDatabase[1], marksList,
                        Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                        Objects.equals(isActiveFromDatabase[1], "true"),
                        Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                        examDateFromDatabase[1], temp);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //update course's marks list
    public static void updateCourseData(File source, File temp, String id, Map<Student, Double> marks) {
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
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
            source.createNewFile();
            reader = new Scanner(temp);
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                        teacherIdFromDatabase[1], marksList,
                        Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                        Objects.equals(isActiveFromDatabase[1], "true"),
                        Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                        examDateFromDatabase[1], temp);
            }
            reader.close();
            temp.delete();
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
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
            source.createNewFile();
            reader = new Scanner(temp);
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

                String teacherPath = "./Database/teachers_data.txt";
                //String teacherPath = ".\\Database\\teachers_data.txt"; //uncomment this for windows
                File teacherFile = new File(teacherPath);

                try {
                    IdFinder.findTeacherByID(teacherIdFromDatabase[1], teacherFile);
                } catch (NotFoundException e) {
                    System.out.println(e.getMessage());
                }

                String studentPath = "./Database/students_data.txt";
                //String studentPath = ".\\Database\\students_data.txt"; //uncomment this for windows
                File studnetFile = new File(studentPath);

                List<Student> listOfStudents = new ArrayList<>();
                if (studentsFromDatabase[1].length() > 2) {
                    String[] studentsIds = studentsFromDatabase[1].substring(1, studentsFromDatabase[1].length() - 1).split("~");
                    for (String stuId : studentsIds) {
                        try {
                            Student tempStudent = IdFinder.findStudentByID(stuId, studnetFile);
                            listOfStudents.add(tempStudent);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String[] markDetails = marksFromDatabase[1].substring(1, marksFromDatabase[1].length() - 1).split("\\*");
                Map<Student, Double> marksList = new HashMap<>();
                if (marksFromDatabase[1].length() > 2) {
                    for (String studentAndMark : markDetails) {
                        String stuId = studentAndMark.split("#")[0];
                        double score = Double.parseDouble(studentAndMark.split("#")[1]);
                        try {
                            Student stu = IdFinder.findStudentByID(stuId, studnetFile);
                            marksList.put(stu, score);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                String assignmentPath = "./Database/assignments_data.txt";
                //String assignmentPath = ".\\Database\\assignments_data.txt"; //uncomment this for windows
                File assignmentFile = new File(assignmentPath);

                List<Assignment> listOfAssignments = new ArrayList<>();
                if (assignmentsFromDatabase[1].length() > 2) {
                    String[] assignmentsIds = assignmentsFromDatabase[1].substring(1, assignmentsFromDatabase[1].length() - 1).split("~");
                    for (String assignId : assignmentsIds) {
                        try {
                            Assignment tempAssignment = IdFinder.findAssignmentByID(assignId, assignmentFile);
                            listOfAssignments.add(tempAssignment);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                StoreData.storeCourse(courseNameFromDatabase[1], idFromDatabase[1],
                        teacherIdFromDatabase[1], marksList,
                        Integer.parseInt(unitsFromDatabase[1]), Integer.parseInt(numOfStudentsFromDatabase[1]), listOfStudents,
                        Objects.equals(isActiveFromDatabase[1], "true"),
                        Integer.parseInt(numOfAssignmentsFromDatabase[1]), listOfAssignments,
                        examDateFromDatabase[1], temp);
            }
            reader.close();
            temp.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

