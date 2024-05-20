import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Assignment {
	private long deadline;
	private boolean isActive;

	public Assignment(LocalDate deadline, boolean isActive){
		this.deadline = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
		this.isActive = isActive;
	}

	public long getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
	}
}