package winter.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String indent = "   ";
    protected LocalDateTime deadline;
    public Deadline (int order,boolean isMarked, String deadlineName, LocalDateTime deadline) {
        super(order,isMarked,deadlineName);
        this.deadline = deadline;
    }
    public LocalDateTime getDeadline(){
        return deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        String typeCheckbox = "[D]";
        return indent + typeCheckbox + " " + this.getDoneCheckbox() + this.getTaskName() +
                    " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

}
