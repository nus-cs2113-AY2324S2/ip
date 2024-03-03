import java.util.NoSuchElementException;

/**
 * The Event class represents an event task, a type of Task.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event object with the specified description.
     * @param description Description of the event task.
     * @throws IllegalArgumentException if the description is empty or contains only "event."
     * @throws NoSuchElementException if the description does not contain "/from" and "/to" keywords.
     */
    public Event(String description) {
        super(description);
        extractDescription(description);
        this.taskType = "[E]";
    }

    /**
     * Constructs an Event object with the specified mark status, description, start time, and end time.
     * @param isDone Mark status of the event task.
     * @param description Description of the event task.
     * @param from Start time of the event task.
     * @param to End time of the event task.
     */
    public Event(boolean isDone, String description, String from, String to) {
        super(description);
        this.isDone = isDone;
        this.from = from;
        this.to = to;
        this.taskType = "[E]";
    }

    private void extractDescription(String description) {
        String[] parts = description.split(" /from ", 2);
        if (description.trim().equalsIgnoreCase("event")) {
            throw new IllegalArgumentException("The description of an event cannot be empty.");
        }
        this.description = parts[0].substring(6).trim(); // Remove "event" command and trim spaces
        if (parts.length != 2) {
            throw new NoSuchElementException("Invalid event format: missing '/from' keyword.");
        }
        parts = parts[1].split(" /to ", 2);

        this.from = parts[0];
        this.to = parts[1];
    }

    /**
     * Retrieves the start time of the event.
     * @return Start time of the event.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Retrieves the end time of the event.
     * @return End time of the event.
     */
    public String getTo() {
        return to;
    }

    /**
     * Converts the event task to a string representation.
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the event task to a string representation for file storage.
     * @return String representation for file storage.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString();
    }
}

