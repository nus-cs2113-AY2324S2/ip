package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final String deadline;

    public Deadline(String description, String by) {
        super(description);
//        System.out.println("description: " + description);
//        System.out.println("by: " + by);
        // if by is not in format YYYY-MM-DD, do not parse
        if (!by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.deadline = by;
            return;
        }
        LocalDate byDate = LocalDate.parse(by.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.deadline = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String saveString() {
        return getType() + " | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + deadline;
    }
}
