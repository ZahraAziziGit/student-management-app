package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {

	private String name;
	private String courseID;
	private Teacher teacher;
	private double highestMark = 0;
	private int numberOfUnits;
	private List<Student> listOfStudents = new ArrayList<>();
	private int numberOfStudents = 0;
	private boolean isCourseActive;
	private List<Assignment> listOfAssignments = new ArrayList<>();
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
		numberOfStudents++;
	}

	public void removeStudent(Student student){
		listOfStudents.remove(student);
		numberOfStudents--;
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

	public void setHighestMark(double highestMark) {
		this.highestMark = highestMark;
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