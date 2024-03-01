package mona.task;

import mona.util.Constants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a description and a due date,
 * which can possibly be stored as a LocalDateTime object.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dateTime;
    protected boolean isLocalDateTimeStored;
    protected DateTimeFormatter inputFormatter;
    protected DateTimeFormatter outputFormatter;

    /**
     * Constructor for Deadline. Initializes the task with a description and a due date,
     * and attempts to parse the due date as a LocalDateTime.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task, in the format specified by Constants.DATE_TIME_INPUT_FORMAT.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.isLocalDateTimeStored = true; // set to true by default, exceptions will set it to false.
        this.inputFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_INPUT_FORMAT);
        this.outputFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_OUTPUT_FORMAT);

        try {
            this.dateTime = LocalDateTime.parse(by, inputFormatter);
        } catch (DateTimeParseException e) {
            this.isLocalDateTimeStored = false;
        }
    }
    /**
     * Returns a string representation of the deadline task, including its type, status, description, and due date.
     * If the due date is stored as a LocalDateTime object, it is formatted according
     *  to Constants.DATE_TIME_OUTPUT_FORMAT.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        String output = "[D]" + super.toString() + " " +  "(" + "by: ";

        if (!isLocalDateTimeStored) {
            output += this.by + ")";
        } else {
            output += this.dateTime.format(outputFormatter) + ")";
        }

        return output;
    }
    /**
     * Returns the full description of the deadline task, including its description and due date.
     *
     * @return The full description of the deadline task.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " "
                + "/by " + this.by;
    }
}
