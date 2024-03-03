/**
 * Represents the {@code ToDo} task for the Jarvas bot.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task object.
     *
     * @param label Label of the {@code ToDo} task.
     */
    public ToDo(String label) {
        super(label);
    }

    /**
     * Retrieves the string representation of the {@code ToDo} task.
     * Inherits the {@code toString} method from the parent {@code Task} class.
     *
     * @return A formatted string representing the {@code ToDo} task,
     * including the symbol representing completion status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Retrieves a string representing the {@code ToDo} task.
     * Inherits the {@code getType} method from the parent {@code Task} class.
     *
     * @return A string representing {@code ToDo} tasks.
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "TODO";
    }
    /**
     * Retrieves a string representing the associated time range to the {@code ToDo} task.
     * Inherits the {@code getRange} method from the parent {@code Task} class, and returns.
     *
     * @return The time range associated with the ToDo task, which is not applicable.
     * {@inheritDoc}
     */
    @Override
    public String getRange() {
        return "";
    }
}
