package classes;

import java.util.*;

public class Student{

	private final String firstName;
	private final String lastName;
	private final String studentID;
	private String password;
	private int numberOfCourses;
	private int numberOfUnits;
	private List<Course> listOfCourses = new ArrayList<>();
	private double totalAverage;
    private Map<Course, Double> marks = new HashMap<>();


	public Student(String firstName, String lastName, String id, int numberOfCourses, int numberOfUnits,
				   List<Course> listOfCourses, double totalAverage, Map<Course, Double> marks){
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = id;
		this.numberOfCourses = numberOfCourses;
		this.numberOfUnits = numberOfUnits;
		this.listOfCourses = listOfCourses;
		this.totalAverage = totalAverage;
		this.marks = marks;
	}


	public void printListOfCourses(){
		for (int i = 0; i < listOfCourses.size(); i++) {
			Course course = listOfCourses.get(i);
			System.out.println(i + ". " + course.getName());
		}
	}

	public void printTotalAverage(){
		calculateTotalAverage();
		System.out.println(totalAverage);
	}

	public void printNumberOfUnits(){
		calculateNumberOfUnits();
		System.out.println(numberOfUnits);
	}

	public void addCourse(Course course) {
		listOfCourses.add(course);
	}

	public void removeCourse(Course course) {
		listOfCourses.remove(course);
	}

	public void calculateNumberOfUnits() {
		int units = 0;
        for (Course course : listOfCourses) {
            units += course.getNumberOfUnits();
        }
		numberOfUnits = units;
	}

	public void calculateNumberOfCourses() {
		numberOfCourses = listOfCourses.size();
	}

	public void calculateTotalAverage(){
		double sum = 0.0;
		for (Course course: marks.keySet()) {
			sum += marks.get(course) * course.getNumberOfUnits();
		}
		calculateNumberOfUnits();
		if (numberOfUnits == 0 || sum == 0)
			totalAverage = 0.0;
		else
			totalAverage = sum / numberOfUnits;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getStudentID() {
		return studentID;
	}

	public String getPassword() {
		return password;
	}

	public int getNumberOfCourses() {
		calculateNumberOfCourses();
		return numberOfCourses;
	}

	public int getNumberOfUnits() {
		calculateNumberOfUnits();
		return numberOfUnits;
	}

	public List<Course> getListOfCourses() {
		return listOfCourses;
	}

	public double getTotalAverage() {
		calculateTotalAverage();
		return totalAverage;
	}

	public Map<Course, Double> getMarks() {
		return marks;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}