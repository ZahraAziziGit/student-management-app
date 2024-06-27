package classes;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Teacher {

	private final String firstName;
	private final String lastName;
	private final String teacherID;
	private int numberOfCourses;
	private final List<Course> listOfCourses = new ArrayList<>();


	public Teacher(String firstName, String lastName, String teacherID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.teacherID = teacherID;
	}


	public void addStudent(Student student, Course course) {
		course.addStudent(student);
	}

	public void removeStudent(Student student, Course course) {
		course.removeStudent(student);
	}

	public void addCourse(Course course){
		listOfCourses.add(course);
		calculateNumberOfCourses();
	}

	public void removeCourse(Course course){
		listOfCourses.remove(course);
		calculateNumberOfCourses();
	}

	public void calculateNumberOfCourses() {
		numberOfCourses = listOfCourses.size();
	}

	public void giveMark(Student student, Course course, double mark) {
		student.getMarks().put(course, mark);
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
		calculateNumberOfCourses();
		return numberOfCourses;
	}

	public List<Course> getListOfCourses() {
		return listOfCourses;
	}
}