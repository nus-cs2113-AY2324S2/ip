package daisy.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The event class handles the creation and saving formatting of event tasks. It is assumed that all event would happen
 * and end within the same day.
 */
public class Event extends Task {

    protected LocalDate Date;
    protected LocalTime fromTime;
    protected LocalTime toTime;


    /**
     * Creates an event instance.
     * @param taskName the task name of the event task
     * @param fromDate the date and from time of the event task
     * @param toTime the ending time of the event task
     */
    public Event(String taskName, String fromDate, String toTime){
        super(taskName);
        String[] dateTime = fromDate.split(" ");
        this.Date = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.fromTime = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm"));
        this.toTime = LocalTime.parse(toTime, DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Returns a String representation of the event in the format such that it is suitable for program printouts.
     * @return a String representation of the event suitable for printing
     */
    public String toString() {
        return String.format("[E][%s] %s (from: %s %s to: %s)", (this.isDone)? "X":" ", this.taskName, this.Date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.fromTime.format(DateTimeFormatter.ofPattern("h:mma")), this.toTime.format(DateTimeFormatter.ofPattern("h:mma")));
    }

    /**
     * Returns a String representation of the event in the format such that it is suitable for loading and saving.
     * @return a String representation of the event suitable for loading and saving
     */
    public String save() {
        return String.format("E,%s,%s,%s %s,%s", (this.isDone)? "1":"0", this.taskName, this.Date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), this.fromTime.format(DateTimeFormatter.ofPattern("HHmm")), this.toTime.format(DateTimeFormatter.ofPattern("HHmm")));
    }

}
