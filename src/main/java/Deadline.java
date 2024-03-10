import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class and handles both date and time information for the deadline.
 */
public class Deadline extends Task {
    private LocalDateTime byDateTime;
    private String byString;
    // Define an array of formatters to support multiple date formats
    private static final DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")
    };
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a Deadline object with the given description, deadline string, and completion status.
     *
     * @param description The description of the deadline task.
     * @param by           The deadline string, which may include date and time information.
     * @param isDone       The completion status of the deadline task.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.byDateTime = parseDateTime(by); // Attempt to parse the date using the defined formatters
        this.byString = (this.byDateTime == null) ? by : null; // If parsing fails, treat by as a regular string
    }

    /**
     * Attempts to parse the provided date and time string using a set of predefined formatters.
     *
     * @param dateTimeStr The date and time string to be parsed.
     * @return The LocalDateTime object representing the parsed date and time, or null if parsing fails.
     */
    private static LocalDateTime parseDateTime(String dateTimeStr) {
        // Try to parse the string with each formatter
        for (DateTimeFormatter formatter : formatters) {
            try {
                // If parsing succeeds, return the LocalDateTime object
                return LocalDateTime.parse(dateTimeStr, formatter);
            } catch (DateTimeParseException e) {
                // If parsing fails, catch the exception and try the next formatter
            }
        }
        // If all formatters fail, return null
        return null;
    }

    /**
     * Returns a string representation of the Deadline object.
     * If byDateTime is not null, the deadline is formatted using the outputFormatter.
     * If byDateTime is null, byString contains the original deadline string.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        if (byDateTime != null) {
            // If byDateTime is not null, format it using the outputFormatter
            return super.toString() + " (by: " + byDateTime.format(outputFormatter) + ")";
        } else {
            // If byDateTime is null, byString contains the original string
            return super.toString() + " (by: " + byString + ")";
        }
    }

    /**
     * Returns a string representation of the Deadline object in a format suitable for saving to a file.
     * If byDateTime is not null, the first formatter's pattern is used when saving to the file.
     * If byDateTime is null, byString contains the original deadline string.
     *
     * @return A string representation of the Deadline object in a format suitable for saving to a file.
     */
    @Override
    public String toSaveFormat() {
        if (byDateTime != null) {
            // For consistency, use the first formatter's pattern when saving to file
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDateTime.format(formatters[0]);
        } else {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byString;
        }
    }

    /**
     * Returns the type identifier for the Deadline task, which is "[D]".
     *
     * @return The type identifier for the Deadline task.
     */
    public String getType() {
        return "[D]";
    }
}








