package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {

	private final String name;
	private final String courseID;
	private Teacher teacher;
	private double highestMark = 0;
	private final int numberOfUnits;
	private final List<Student> listOfStudents = new ArrayList<>();
	private int numberOfStudents = 0;
	private boolean isCourseActive;
	private final List<Assignment> listOfAssignments = new ArrayList<>();
	private int numberOfDefinedAssignments;
	private int numberOfActiveAssignments;
	private LocalDate dateOfExam;

	public Course(String name, String courseID, Teacher teacher, int numberOfUnits, LocalDate dateOfExam,
				  boolean isCourseActive){
		this.name = name;
		this.courseID = courseID;
		this.teacher = teacher;
		this.numberOfUnits = numberOfUnits;
		this.dateOfExam = dateOfExam;
		this.isCourseActive = isCourseActive;
	}


	public void printListOfStudents(){
		for (int i = 0; i < listOfStudents.size(); i++) {
			Student student = listOfStudents.get(i);
			System.out.println(i + ". " + student.getFirstName()
					+ " " + student.getLastName() + ", Student ID: " + student.getStudentID());
		}
	}

	public void addStudent(Student student){
		listOfStudents.add(student);
		calculateNumberOfStudents();
	}

	public void removeStudent(Student student){
		listOfStudents.remove(student);
		calculateNumberOfStudents();
	}

	public void calculateNumberOfStudents() {
		numberOfStudents = listOfStudents.size();
	}

	public void findHighestMark(){
		System.out.println(highestMark);
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

	public double getHighestMark() {
		return highestMark;
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

	public LocalDate getDateOfExam() {
		return dateOfExam;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setHighestMark(double highestMark) {
		this.highestMark = highestMark;
	}

	public void setDateOfExam(LocalDate dateOfExam) {
		this.dateOfExam = dateOfExam;
	}

	public void setCourseActive(boolean courseActive) {
		isCourseActive = courseActive;
	}


	@Override
	public int hashCode() {
		return Integer.parseInt(courseID);
	}

	@Override
	public boolean equals(Object obj) {
		return ((Course) obj).getCourseID().equals(this.getCourseID());
	}
}
