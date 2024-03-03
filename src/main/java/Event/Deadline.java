package Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The <code>Deadline</code> class represents a deadline task in the task management
 * application. A deadline is a task that needs to be completed by a specific time.
 * This class extends <code>Task</code> class to add specific functionality for deadline
 * tasks.
 */
public class Deadline extends Task {
    private final LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    /**
     * Constructs a Deadline task with a description and deadline time.
     *
     * @param input The raw input string containing the task description and deadline time.
     */
    public Deadline(String input) {
        super(input);
        String[] parts = input.split(" /by ", 2);
        this.description = parts[0].substring(9).trim();
        this.by = LocalDateTime.parse(parts[1].trim(), INPUT_FORMAT);
        this.eventType = "[D]";
    }

    /**
     * Returns a string representation of the Deadline task, including its description
     * and deadline time.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Provides a string formatted for storage purposes, including the task type,
     * status, description, and deadline time.
     *
     * @return A string representation of the Deadline task for storage purposes.
     */
    @Override
    public String toStorageString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(INPUT_FORMAT);
    }
}