package Tasks;


import UserInputs.Parser;
import commands.Command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline, extending the Task class.
 * It includes information about the due date and time of the task.
 */
public class Deadline extends Task {

    protected LocalDate by;
    protected LocalTime byTime;
    protected String deadLineString;

    /**
     * Constructs a {@code Deadline} task with the specified description and due date/time.
     *
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline task, represented as a LocalDate.
     * @param byTime      The due time of the deadline task, represented as a LocalTime.
     */
    public Deadline(String description, LocalDate by, LocalTime byTime) {
        super(description);
        this.by = by;
        this.byTime = byTime;
        this.deadLineString = by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + byTime.format(DateTimeFormatter.ofPattern("h:mma"));
    }

    /**
     * Retrieves the status icon and description of the deadline task.
     *
     * @return A string representing the status icon, description, and deadline of the task.
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "[D][X] " + super.description + " (by: " + deadLineString + ")": "[D][ ] " + super.description + " (by: " + deadLineString + ")"); // mark done task with X
    }

    /**
     * Generates a string in the required file format for saving the Deadline task.
     *
     * @return A string representing the Deadline task in the required file format.
     */
    @Override
    public String printFileFormat() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
        return super.printFileFormat() + " | " + this.by.format(inputFormatter) + " " + this.byTime.format(inputTimeFormatter);
    }
}