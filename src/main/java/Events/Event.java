package Events;

public class Event extends Task {
    private String from;
    private String to;


    /**
     * Constructs an Event task by parsing the input string to extract the description,
     * start time/date, and end time/date.
     *
     * @param input A string containing the event description and timing information,
     *              expected to follow the format "description /from start /to end".
     */
    public Event(String input) {
        super(extractDescription(input));
        this.from = extractFrom(input);
        this.to = extractTo(input);
    }

    /**
     * Extracts the description part of the input string.
     *
     * @param input The full input string containing an event description and timing.
     * @return The extracted event description.
     * @throws IllegalArgumentException If the input string does not contain the required
     *      *                                  '/from' and '/to' parts for extracting event timing information.
     */
    private static String extractDescription(String input) {
        if (!input.contains(" /from ")) {
            throw new IllegalArgumentException("Invalid event format. Ensure you use ' /from ' to specify the start date/time.");
        }
        return input.substring(0, input.indexOf(" /from ")).trim();
    }



    /**
     * Extracts the 'from' part (start time/date) from the input string.
     *
     * @param input The full input string containing timing information.
     * @return The extracted start time/date of the event.
     */
    private static String extractFrom(String input) {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new IllegalArgumentException("Invalid event format. Ensure you use ' /from ' and ' /to ' to specify the event period.");
        }
        return input.substring(input.indexOf(" /from ") + 7, input.indexOf(" /to ")).trim(); // "+ 7" to skip past " /from "
    }

    /**
     * Extracts the 'to' part (end time/date) from the input string.
     *
     * @param input The full input string containing timing information.
     * @return The extracted end time/date of the event.
     */
    private static String extractTo(String input) {
        if (!input.contains(" /to ")) {
            throw new IllegalArgumentException("Invalid event format. Ensure you use ' /to ' to specify the end date/time.");
        }
        return input.substring(input.indexOf(" /to ") + 5).trim(); // "+ 5" to skip past " /to "
    }


    /**
     * Returns a string representation of the Event task, including its type, completion status,
     * description, and timing information.
     *
     * @return A string representation of the Event task.
     */
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description+ " from " + from + " to " + to ;
    }

    /**
     * Formats the Event task for file storage, including its type, completion status,
     * description, and timing information.
     *
     * @return A formatted string suitable for file storage.
     */
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | from" + from + " | to " + to;
    }
}
