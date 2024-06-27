package classes;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Teacher {

	private String teacherID;
	private String firstName;
	private String lastName;
	private int numberOfCourses;
	private List<Course> listOfCourses = new ArrayList<>();

	public Teacher(String firstName, String lastName, String teacherID) {
		this.teacherID = teacherID;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public void addStudent(Student student, Course course) {
		course.addStudent(student);
	}

	public void removeStudent(Student student, Course course) {
		course.removeStudent(student);
	}

	public void addCourse(Course course){
		listOfCourses.add(course);
		numberOfCourses++;
	}

	public void removeCourse(Course course){
		listOfCourses.remove(course);
		numberOfCourses--;
	}

	public void giveMark(Student student, Course course, double mark) {
		student.marks.put(course, mark);
		if (mark > course.getHighestMark()) {
			course.setHighestMark(mark);
		}
	}

	public void changeDeadline(Assignment assignment, LocalDate newDeadline) {
		assignment.setDeadline(newDeadline);
	}

	public void addAssignment(Course course, Assignment assignment){
		course.getListOfAssignments().add(assignment);
	}
	
	public void removeAssignment(Course course, Assignment assignment){
		course.getListOfAssignments().remove(assignment);
	}


	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public int getNumberOfCourses() {
		return numberOfCourses;
	}

	public List<Course> getListOfCourses() {
		return listOfCourses;
	}
}