package classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Assignment {

	private long untilDeadline;
	private LocalDate deadline;
	private boolean isActive;
	private final String assignmentID;
	private final Course course;


	public Assignment(String assignmentID, LocalDate deadline, boolean isActive, Course course){
		this.assignmentID = assignmentID;
		this.deadline = deadline;
		this.untilDeadline = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
		this.isActive = isActive;
		this.course = course;
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

	public Course getCourse() {
		return course;
	}


	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
		this.untilDeadline = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
	}

	public void setActive(boolean active) {
		isActive = active;
	}
}