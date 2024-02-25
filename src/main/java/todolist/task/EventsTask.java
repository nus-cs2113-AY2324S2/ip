package todolist.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class EventsTask extends Task {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructor for EventsTask
     * @param name the name of the task
     * @param startDateTime the start date of the task
     * @param endDateTime the end date of the task
     */
    public EventsTask(String name, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(name);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public EventsTask(String name, boolean isDone, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(name, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String storeDataString() {
        return "E" + "|" + (this.isDone ? 1 : 0) + "|" + this.name + "|" + this.startDateTime + "|" + this.endDateTime;
    }

    /**
     * Get the full String representation of the task
     * @return the full String of the task
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM dd, uuuu hh:mm:ss a", Locale.ENGLISH);
        String formattedStartDateTime = this.startDateTime.format(outputFormatter);
        String formattedEndDateTime = this.startDateTime.format(outputFormatter);
        return "[E]" + super.toString() + " (from: " + formattedStartDateTime + " to: " + formattedEndDateTime + ")";
    }

}
