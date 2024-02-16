package duke.task;

/**
 * Represents the Event task of the Duke chatbot.
 * Event tasks are tasks with a start and end date/time.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs a new Event object with the specified description, start date/time and end date/time.
     * @param description Description of the Event task
     * @param from Start date/time of the Event task
     * @param to End date/time of the Event task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event task to the specific format for saving to the text file.
     *
     * @return A formatted string containing the Event task for saving.
     */
    @Override
    public String toDisk() {
        return "E" + super.toDisk() + " | " + this.from + " - " + this.to + System.lineSeparator();
    }

    /**
     * Converts the Event task to the specific format for displaying on the screen.
     *
     * @return A formatted string containing the Event task for display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
