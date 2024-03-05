/**
 * Represents the {@code Event} task including a label and time interval information for the Jarvas bot.
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Constructs a new {@code Event} task object.
     *
     * @param label Label of the {@code Event} task.
     * @param from Start time for the {@code Event} task.
     * @param to End time for the {@code Event} task.
     */
    public Event(String label, String from, String to) {
        super(label);
        this.from = from;
        this.to = to;
    }

    /**
     * Parses a string containing Event information, extracts the label, start time and end time before returning
     * an array of strings containing the information.
     *
     * @param input A string containing the Event information in the format "label /from start /to end".
     * @return An array of strings containing the extracted label, start time and end time.
     */
    public static String[] getInterval(String input) {

        String[] results = new String[Constant.EVENT_PARAMETERS];


        int indexFrom = input.indexOf("/from");
        int indexTo = input.indexOf("/to");

        String label = input.substring(0, indexFrom).trim();


        String fromSubstring = input.substring(indexFrom + Constant.EVENT_FROM_OFFSET, indexTo).trim();
        String toSubstring = input.substring(indexTo + Constant.EVENT_TO_OFFSET).trim();

        results[0] = label;
        results[1] = fromSubstring;
        results[2] = toSubstring;

        return results;
    }

    /**
     * Retrieves the string representation of the {@code Event} task.
     * Inherits the {@code toString} method from the parent {@code Task} class.
     *
     * @return A formatted string representing the {@code Event} task,
     * including the symbol representing completion status, description and the time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Retrieves a string representing the {@code Event} task.
     * Inherits the {@code getType} method from the parent {@code Task} class.
     *
     * @return A string representing {@code Event} tasks.
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "EVENT";
    }
    /**
     * Retrieves a string representing the associated time range to the {@code Event} task.
     * Inherits the {@code getRange} method from the parent {@code Task} class, and returns.
     *
     * @return The time range associated with the Event task, which is the start and end time.
     * {@inheritDoc}
     */
    @Override
    public String getRange() {
        return from + ":" + to;
    }
}

