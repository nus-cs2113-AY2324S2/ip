package BobBot.tasks;

import java.time.format.DateTimeFormatter;

import BobBot.exceptions.InvalidDeadlineException;
import BobBot.parser.DateParser;

/**
 * Implements a deadline task that stores the description of the task and the
 * deadline of the task.
 * 
 * <p> The description is stored in the format <code>[task] /by [deadline]</code>.</p>
 * 
 * @author NicholasTanYY
 * @since January 2024
 * @version 1.0
 */
public class Deadline extends Task {

    protected String task;
    protected String by;
    protected String parsedDateString;

    /**
     * Creates a deadline task with the given description.
     * 
     * @param description The description of the deadline task.
     * @throws InvalidDeadlineException If the task description is empty or 
     * does not contain a deadline.
     */
    public Deadline(String description) throws InvalidDeadlineException {
        super(description);

        if (!description.contains("/by")) {
            throw new InvalidDeadlineException();
        }

        this.task = this.description.substring(
                "Deadline".length(),
                this.description.indexOf("/by")).trim();
        this.by = this.description.substring(
                this.description.indexOf("/by") + "/by".length()).trim();

        if (DateParser.containsValidDateString(this.by)) {
            String dateString = DateParser.detectDateFromByString(this.by);
            this.parsedDateString = DateParser.parseDateFromDateString(dateString)
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.by = this.by.replace(dateString, this.parsedDateString);
        }

        if (this.task.length() == 0 || this.by.length() == 0) {
            throw new InvalidDeadlineException();
        }
    }

    /**
     * Returns the string representation of the deadline task.
     * 
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.task + " (by: " + by + ")";
    }

}
