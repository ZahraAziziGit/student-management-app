import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
	private String firstName;
	private String lastName;
	private String studentID;
	private String password;
	private int numberOfCourses;
	private int numberOfUnits;
	private int numberOfRegistrationUnits;
	private List<Course> listOfCourses = new ArrayList<>();
	private double totalAverage;
	private double currentTermAverage;
	Map<Double, Double> marks = new HashMap<>();

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

	public void calculateTotalAverage(){
		double sumMarks = 0;
		double sumUnits = 0;
		for (Map.Entry<Double, Double> mark : marks.entrySet()) {
			sumMarks += mark.getKey() * mark.getValue();
			sumUnits += mark.getValue();
		}
		totalAverage = sumMarks / sumUnits;
	}

	public void printTotalAverage(){
		calculateTotalAverage();
		System.out.println(totalAverage);
	}

	public void printNumberOfRegistrationUnits(){
		System.out.println(numberOfRegistrationUnits);
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

}