import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Assignment {
	private long deadline;
	private boolean isActive;
	private String assignmentID;


	public Assignment(LocalDate deadline, boolean isActive){
		this.deadline = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
		this.isActive = isActive;
	}


	public long getDeadline() {
		return deadline;
	}

	public String getAssignmentID() {
		return assignmentID;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
	}
}