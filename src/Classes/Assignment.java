package Classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Assignment {

	private long untilDeadline;
	private LocalDate deadline;
	private boolean isActive;
	private final String assignmentID;
	private String courseID;


	public Assignment(String assignmentID, LocalDate deadline, boolean isActive, String courseID){
		this.assignmentID = assignmentID;
		this.deadline = deadline;
		this.untilDeadline = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
		this.isActive = isActive;
		this.courseID = courseID;
	}


	public long getUntilDeadline() {
		return untilDeadline;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public boolean isActive() {
		return isActive;
	}

	public String getAssignmentID() {
		return assignmentID;
	}

	public String getCourseID() {
		return courseID;
	}


	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
		this.untilDeadline = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
}