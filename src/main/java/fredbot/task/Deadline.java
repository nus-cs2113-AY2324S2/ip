package fredbot.task;

import fredbot.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline that the user wants to add to their task list.
 * A Deadline is a type of Task that also includes a deadline by date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs the Deadline object.
     *
     * @param description Description of the deadline task.
     * @param by Deadline date.
     * @throws EmptyDescriptionException If the description of the deadline is empty.
     * @throws DateTimeParseException If the date provided is not in the right format.
     */
    public Deadline(String description, String by) throws EmptyDescriptionException, DateTimeParseException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.by = LocalDate.parse(by);
    }

    /**
     * Converts the deadline to a string for printing purposes.
     *
     * @return The done status, description, and deadline date of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Converts the deadline to a string for saving purposes.
     *
     * @return The done status, description, and deadline date of the deadline.
     */
    public String saveString() {
        return "D" + super.saveString() + " | " + by;
    }
}
