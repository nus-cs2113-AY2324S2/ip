package schmidt.task;

/**
 * Represents an event with a start and end time.
 */
public class Event extends Task {
    public static final String LETTER = "E";
    public static final String REGEX_FROM_DELIMITER = "(?<!\\w)/from(?=\\s|$)";
    public static final String REGEX_TO_DELIMITER = "(?<!\\w)/to(?=\\s|$)";
    public static final String INCORRECT_FORMAT_MESSAGE = "Please follow the event command format\n" +
            "\tevent <description> /from <start> /to <end>";
    public static final int DESCRIPTION_INDEX = 0;
    public static final int FROM_INDEX = 1;
    public static final int TO_INDEX = 2;

    private final String from;
    private final String to;

    /**
     * Constructs an event with the specified description, start time and end time.
     *
     * @param description the description of the event.
     * @param from the start time of the event.
     * @param to the end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time of the event.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time of the event.
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event.
     */
    @Override
    public String toString() {
        return "[" + LETTER + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
