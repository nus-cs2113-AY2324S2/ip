package Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    public Event(String input) {

        super(input);
        String[] firstPart = input.split(" /from ", 2);
        this.description = firstPart[0].substring(6).trim();
        String[] secondPart = firstPart[1].split(" /to ", 2);
        this.from = LocalDateTime.parse(secondPart[0].trim(), INPUT_FORMAT);
        this.to = LocalDateTime.parse(secondPart[1].trim(), INPUT_FORMAT);
        this.eventType = "[E]";

    }
    @Override
    public String toString() {
        return super.toString() + " (from: " + from.format(OUTPUT_FORMAT) +
                " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toStorageString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                from.format(INPUT_FORMAT) + " to " + to.format(INPUT_FORMAT);
    }
}
