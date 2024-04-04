import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    /**
     * Represents an event task with a start and end time.
     * Inherits from the Task class and adds time-specific information
     * */

public class EventTask extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs an EventTask with the specified description, start time, and end time.
     *
     * @param description Description of the event task.
     * @param startTime Start time of the event.
     * @param endTime End time of the event.
     */
    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the EventTask, including its type, description,
     * start time, and end time formatted as "dd-MM-yyyy HH:mm".
     *
     * @return Formatted string representing the EventTask.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedStartTime = this.startTime.format(formatter);
        String formattedEndTime = this.endTime.format(formatter);
        return "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
    }

    @Override
    public String toFileString() {
        return "E | "+ this.getDescription()  + String.format(" | %s | %s | %s", super.toFileString(), this.startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")), this.endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
    }
}