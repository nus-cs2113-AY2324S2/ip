import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents an event task in the Dul application.
 */
public class EventTask extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}


