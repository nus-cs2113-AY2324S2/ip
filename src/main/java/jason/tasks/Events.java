package jason.tasks;


/**
 * Represents an event task with a start and end time.
 */
public class Events extends Task {

    private final String from;
    private final String to;

    /**
     * Creates a new Events task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Events task.
     *
     * @return The string representation of the Events task.
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Parses an Events task from a formatted string.
     * This method is used to recreate an Events task from data stored in a file.
     *
     * @param data The string containing the stored data of the Events task.
     * @return The Events task recreated from the stored data, or null if the data is improperly formatted.
     */
    public static Task parseFromFile(String data) {
        boolean isDone = data.charAt(4) == 'X';
        int fromIndex = data.indexOf("(from: ");
        if (fromIndex == -1) { 
            return null;
        }
        String description = data.substring(6, fromIndex).trim();
        int toIndex = data.indexOf(" to: ", fromIndex);
        if (toIndex == -1) {
            return null;
        }
        String from = data.substring(fromIndex + 7, toIndex).trim();
        String to = data.substring(toIndex + 5, data.length() - 1).trim();

        Events task = new Events(description, from, to);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

}
