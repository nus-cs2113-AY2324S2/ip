package daisy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The event class handles the creation and saving formatting of event tasks. It is assumed that all event would happen
 * and end within the same day.
 */
public class Event extends Task {

    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;


    /**
     * Creates an event instance.
     * @param taskName the task name of the event task
     * @param fromTime the starting date and time of the event task
     * @param toTime the ending date and time of the event task
     */
    public Event(String taskName, String fromTime, String toTime){
        super(taskName);
        this.fromTime = LocalDateTime.parse(fromTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.toTime = LocalDateTime.parse(toTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a String representation of the event in the format such that it is suitable for program printouts.
     * @return a String representation of the event suitable for printing
     */
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", (this.isDone)? "X":" ", this.taskName, this.fromTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")), this.toTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")));
    }

    /**
     * Returns a String representation of the event in the format such that it is suitable for loading and saving.
     * @return a String representation of the event suitable for loading and saving
     */
    public String save() {
        return String.format("E,%s,%s,%s,%s", (this.isDone)? "1":"0", this.taskName, this.fromTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), this.toTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

}
