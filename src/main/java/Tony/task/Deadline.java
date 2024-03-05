package Tony.task;

public class Deadline extends Task {

    public String by;

    /**
     * Represents a {@code Deadline} object that saves task type <code>deadline</code>
     * @param description is the String <code>description</code> for deadline tasks
     * @param by is the String to specify deadline due <code>by</code> time/date
     */

    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
    }

    /**
     * Returns the type of tasks to identify deadline.
     * @return String <code>[D]</code> as task type.
     */

    public String type() {
        return "[D]";
    }

    /**
     * Returns the String to save in the current task list
     * @return String formatted to be saved in task list.
     */

    @Override
    public String toString() {
        return type() + super.toString() + " (by: " + by + ")";
    }
}
