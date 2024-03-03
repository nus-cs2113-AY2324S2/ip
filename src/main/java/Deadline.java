/**
 * Represents the {@code Deadline} task with a label and a due date for the Jarvas bot.
 */
public class Deadline extends Task {
    protected String due;

    /**
     * Constructs a new {@code Deadline} task object.
     *
     * @param label Label of the {@code Deadline} task.
     * @param due Deadline for the {@code Deadline} task.
     */
    public Deadline(String label, String due) {
        super(label);
        this.due = due;
    }

    /**
     * Parses a string containing Deadline information, extracts the label and due date before returning
     * an array of strings containing the information.
     *
     * @param input A string containing the Deadline information in the format "label /by deadline".
     * @return An array of strings containing the extracted label and due date.
     */
    public static String[] getDeadline(String input) {

        String[] results = new String[Constant.DEADLINE_PARAMETERS];


        int index = input.indexOf("/by");

        String label = input.substring(0, index).trim();


        String deadline = input.substring(index + Constant.DEADLINE_BY_OFFSET).trim();

        results[0] = label;
        results[1] = deadline;

        return results;
    }

    /**
     * Retrieves the string representation of the {@code Deadline} task.
     * Inherits the {@code toString} method from the parent {@code Task} class.
     *
     * @return A formatted string representing the {@code Deadline} task,
     * including the symbol representing completion status, description and the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due + ")";
    }

    /**
     * Retrieves a string representing the {@code Deadline} task.
     * Inherits the {@code getType} method from the parent {@code Task} class.
     *
     * @return A string representing {@code Deadline} tasks.
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "DEADLINE";
    }
    /**
     * Retrieves a string representing the associated deadline to the {@code Deadline} task.
     * Inherits the {@code getRange} method from the parent {@code Task} class, and returns.
     *
     * @return The time range associated with the Deadline task, which is the due date.
     * {@inheritDoc}
     */
    @Override
    public String getRange() {
        return due;
    }
}

