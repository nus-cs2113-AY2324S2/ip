package Tasks;

import Tasks.Task;
import UserInputs.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * The Event class represents a task that is an event with a specific start and end time, extending the Task class.
 * It includes information about the event's start and end date and time.
 */
public class Event extends Task {
    protected LocalTime fromTime;
    protected LocalDate fromDate;
    protected LocalTime toTime;
    protected LocalDate toDate;
    protected String DateTimeString;

    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The name of the event task
     * @param fromDate The start date of the deadline task, represented as a LocalDate.
     * @param fromTime The start time of the deadline task, represented as a LocalTime.
     * @param toDate The end date of the deadline task, represented as a LocalDate.
     * @param toTime The end time of the deadline task, represented as a LocalTime.
     */
    public Event(String description, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        super(description);
        this.fromTime = fromTime;
        this.fromDate = fromDate;
        this.toTime = toTime;
        this.toDate = toDate;
        this.DateTimeString = "from: " + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                fromTime.format(DateTimeFormatter.ofPattern("h:mma")) + " to: " +
                toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + toTime.format(DateTimeFormatter.ofPattern("h:mma"));
    }

    /**
     * Retrieves the status icon and description of the event task.
     *
     * @return A string representing the status icon, description, and start/end date and time of the event task.
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "[E][X] " + super.description + " (" + DateTimeString + ")": "[E][ ] " + super.description +
                " (" + DateTimeString + ")"); // mark done task with X
    }

    /**
     * Generates a string in the required file format for saving the Event task.
     *
     * @return A string representing the Event task in the required file format.
     */
    @Override
    public String printFileFormat() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
        return super.printFileFormat() + " | " + fromDate.format(inputFormatter) + " " +
                fromTime.format(inputTimeFormatter) + " - " + toDate.format(inputFormatter) + " " +
                toTime.format(inputTimeFormatter);
    }
}
