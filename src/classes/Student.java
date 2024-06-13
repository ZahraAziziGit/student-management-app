package classes;

import java.util.*;

public class Student{
	private String firstName;
	private String lastName;
	private String studentID;
	private String password;
	private int numberOfCourses;
	private int numberOfUnits;
	private List<Course> listOfCourses = new ArrayList<>();
	private double totalAverage;
	private double currentTermAverage;
	public Map<Course, Double> marks = new HashMap<>();

	public Student(String firstName, String lastName, String id){
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = id;
	}


	public void printListOfCourses(){
		for (int i = 0; i < listOfCourses.size(); i++) {
			Course course = listOfCourses.get(i);
			System.out.println(i + ". " + course.getName());
		}
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
		totalAverage = sum / numberOfUnits;
	}

	public void printTotalAverage(){
		calculateTotalAverage();
		System.out.println(totalAverage);
	}

	public void printNumberOfUnits(){
		System.out.println(numberOfUnits);
	}

	public void calculateNumberOfUnits() {
		int numOfUnits = 0;
        for (Course course : listOfCourses) {
            numOfUnits += course.getNumberOfUnits();
        }
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
		return numberOfUnits;
	}

	public List<Course> getListOfCourses() {
		return listOfCourses;
	}
}