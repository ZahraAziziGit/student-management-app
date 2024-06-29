package Classes;

import Exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Teacher {

	private final String firstName;
	private final String lastName;
	private final String teacherID;
	private int numberOfCourses;
	private List<Course> listOfCourses = new ArrayList<>();


	public Teacher(String firstName, String lastName, String teacherID, int numberOfCourses, List<Course> listOfCourses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.teacherID = teacherID;
		this.numberOfCourses = numberOfCourses;
		this.listOfCourses = listOfCourses;
	}

	public boolean doesCourseExist(Course course) {
		boolean doesCourseExists = false;
		for (Course cou : listOfCourses) {
			if (cou.getCourseID().equals(course.getCourseID())) {
				doesCourseExists = true;
				break;
			}
		}
		return doesCourseExists;
	}

	public void addCourse(Course course) throws AlreadyExistsException {
		if (doesCourseExist(course))
			throw new AlreadyExistsException(course.getName(), course.getCourseID(), firstName +
					" " + lastName, "list of courses");
		else {
			listOfCourses.add(course);
			calculateNumberOfCourses();
		}
	}

	public void removeCourse(Course course) throws NotFoundException {
		if (!doesCourseExist(course))
			throw new NotFoundException(course.getName(), course.getCourseID(), firstName +
					" " + lastName, "list of courses");
		else {
			listOfCourses.remove(course);
			calculateNumberOfCourses();
		}
	}

	public void addStudent(Student student, Course course) throws AlreadyExistsException {
		course.addStudent(student);
	}

	public void removeStudent(Student student, Course course) throws NotFoundException {
		course.removeStudent(student);
	}

	public void calculateNumberOfCourses() {
		numberOfCourses = listOfCourses.size();
	}

	public void giveMark(Student student, Course course, double mark) throws NotFoundException {
		student.addMark(course, mark);
	}

	public void changeDeadline(Assignment assignment, LocalDate newDeadline) {
		assignment.setDeadline(newDeadline);
	}

	public void addAssignment(Course course, Assignment assignment) throws AlreadyExistsException {
		course.addAssignment(assignment);
	}
	
	public void removeAssignment(Course course, Assignment assignment) throws NotFoundException {
		course.removeAssignment(assignment);
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