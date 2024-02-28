package Gene.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        taskType = "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    public String toFileString() {
        return super.toFileString() + " | " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | "
                + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
