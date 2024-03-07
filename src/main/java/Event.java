import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.end = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + description + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + " to: " + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " + end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}