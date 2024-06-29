package Classes;

import Exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {

    private final String name;
    private final String courseID;
    private Teacher teacher;
    private Map<Student, Double> marks;
    private final int numberOfUnits;
    private List<Student> listOfStudents = new ArrayList<>();
    private int numberOfStudents;
    private boolean isCourseActive;
    private List<Assignment> listOfAssignments = new ArrayList<>();
    private int numberOfAssignments;
    private int numberOfActiveAssignments;
    private LocalDate dateOfExam;


    public Course(String name, String courseID, Teacher teacher, Map<Student, Double> marks, int numberOfUnits,
                  List<Student> listOfStudents, int numberOfStudents, boolean isCourseActive,
                  List<Assignment> listOfAssignments, int numberOfAssignments, LocalDate dateOfExam) {
        this.name = name;
        this.courseID = courseID;
        this.teacher = teacher;
        this.marks = marks;
        this.numberOfUnits = numberOfUnits;
        this.listOfStudents = listOfStudents;
        this.numberOfStudents = numberOfStudents;
        this.isCourseActive = isCourseActive;
        this.listOfAssignments = listOfAssignments;
        this.numberOfAssignments = numberOfAssignments;
        this.dateOfExam = dateOfExam;
    }


    public void printListOfStudents() {
        for (int i = 0; i < listOfStudents.size(); i++) {
            Student student = listOfStudents.get(i);
            System.out.println(i + ". " + student.getFirstName()
                    + " " + student.getLastName() + ", Student ID: " + student.getStudentID());
        }
    }

    public boolean doesStudentExists(Student student) {
        boolean doesStudentExists = false;
        for (Student stu : listOfStudents) {
            if (stu.getStudentID().equals(student.getStudentID())) {
                doesStudentExists = true;
                break;
            }
        }
        return doesStudentExists;
    }

    public boolean doesAssignmentExists(Assignment assignment) {
        boolean doesAssignmentExists = false;
        for (Assignment assign : listOfAssignments) {
            if (assign.getAssignmentID().equals(assignment.getAssignmentID())) {
                doesAssignmentExists = true;
                break;
            }
        }
        return doesAssignmentExists;
    }

    public void addStudent(Student student) throws AlreadyExistsException {
        if (doesStudentExists(student))
            throw new AlreadyExistsException(student.getFirstName() + " " + student.getLastName(),
                    student.getStudentID(), name, "list of students");
        else {
            listOfStudents.add(student);
            calculateNumberOfStudents();
        }
    }

    public void removeStudent(Student student) throws NotFoundException {
        if (!doesStudentExists(student))
            throw new NotFoundException(student.getFirstName() + " " + student.getLastName(),
                    student.getStudentID(), name, "list of students");
        else {
            listOfStudents.remove(student);
            calculateNumberOfStudents();
        }
    }

    public void addAssignment(Assignment assignment) throws AlreadyExistsException {
        if (doesAssignmentExists(assignment))
            throw new AlreadyExistsException(assignment.getAssignmentID(), name, "list of assignments");
        else {
            listOfAssignments.add(assignment);
            calculateNumberOfAssignments();
        }
    }

    public void removeAssignment(Assignment assignment) throws NotFoundException {
        if (!doesAssignmentExists(assignment))
            throw new NotFoundException(assignment.getAssignmentID(), name, "list of assignments");
        else {
            listOfAssignments.remove(assignment);
            calculateNumberOfAssignments();
        }
    }

    public void calculateNumberOfStudents() {
        numberOfStudents = listOfStudents.size();
    }

    public void calculateNumberOfAssignments() {
        numberOfAssignments = listOfAssignments.size();
    }

    public Map<Student, Double> findHighestMark() throws NotFoundException {
        Map<Student, Double> highest = new HashMap<>();
        double highestMark = -1.0;
        Student topStudent = null;
        for (Student student : marks.keySet()) {
            if (marks.get(student) > highestMark) {
                highestMark = marks.get(student);
                topStudent = student;
            }
        }
        if (topStudent == null || highestMark == -1.0)
            throw new NotFoundException("There is no students in \"" + name + "\"'s list");
        highest.put(topStudent, highestMark);
        return highest;
    }


    public String getName() {
        return name;
    }

    public String getCourseID() {
        return courseID;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Map<Student, Double> getMarks() {
        return marks;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public int getNumberOfStudents() {
        calculateNumberOfStudents();
        return numberOfStudents;
    }

    public boolean isCourseActive() {
        return isCourseActive;
    }

    public List<Assignment> getListOfAssignments() {
        return listOfAssignments;
    }

    public int getNumberOfAssignments() {
        calculateNumberOfAssignments();
        return numberOfAssignments;
    }

    public LocalDate getDateOfExam() {
        return dateOfExam;
    }


    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setMarks(Map<Student, Double> marks) {
        this.marks = marks;
    }

    public void setDateOfExam(LocalDate dateOfExam) {
        this.dateOfExam = dateOfExam;
    }

    public void setCourseActive(boolean courseActive) {
        isCourseActive = courseActive;
    }
}
