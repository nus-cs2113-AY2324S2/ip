package Nick.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String symbol = "D";
    protected String deadline;
    protected LocalDate deadlineDate;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;

        deadlineDate = LocalDate.parse(deadline);
    }

    public Deadline(String description, String deadline, boolean taskDone) {
        super(description);
        this.deadline = deadline;
        super.isDone = taskDone;
    }
    @Override
    public String toString() {
        return "\t" + "[" + symbol + "]" + "[" + super.getStatusIcon() + "] " + description + " (by: " + getDate() + ")";
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDate() {
        return deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
