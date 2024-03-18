package Events;

public class Deadline extends Task {
    protected String by; // Due date/time


    /**
     * Constructs a Deadline task by parsing the input string to extract the task's description and its due date/time.
     *
     * @param input The full input string containing the task description followed by "/by" and the due date/time.
     */
    public Deadline(String input) {
        super(extractDescription(input));
        this.by = extractBy(input);
    }

    /**
     * Extracts the task description from the input string.
     *
     * @param input The full input string.
     * @return The extracted task description.
     * @throws IllegalArgumentException If the input format is invalid and does not contain "/by".
     */
    private static String extractDescription(String input) {
        if (!input.contains(" /by ")) {
            throw new IllegalArgumentException("Invalid deadline format. Ensure you use ' /by ' to specify the deadline.");
        }
        return input.substring(0, input.indexOf(" /by ")).trim();
    }


    /**
     * Extracts the 'by' (deadline) part from the input string.
     *
     * @param input The full input string.
     * @return The extracted deadline (due date/time).
     * @throws IllegalArgumentException If the input format is invalid and does not contain "/by".
     */
    private static String extractBy(String input) {
        if (!input.contains(" /by ")) {
            throw new IllegalArgumentException("Invalid deadline format. Ensure you use ' /by ' to specify the deadline.");
        }
        return input.substring(input.indexOf(" /by ") + 5).trim(); // "+ 5" to skip past " /by "
    }

    /**
     * Returns a string representation of the Deadline task, including its type, status, description, and due date/time.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description+ " by " +by ;
    }


    /**
     * Formats the Deadline task for file storage, including its type, status, description, and due date/time.
     *
     * @return A formatted string suitable for file storage.
     */
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | by " + by;
    }
}
