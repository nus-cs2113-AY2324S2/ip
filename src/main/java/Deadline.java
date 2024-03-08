/**
 * Implements a deadline task that stores the description of the task in the form
 * of task and by. Indicating the deadline and description of the task.
 *
 *
 * @author nigelheng
 * @since February 2024
 * @version 1.0
 */
public class Deadline extends Task {

    protected String task;
    protected String by;

    public Deadline(String description) throws InvalidDeadlineException {
        super(description);

        if(!description.contains("/by")) {
            throw new InvalidDeadlineException();
        }

        this.task = this.description.substring(
                "Deadline".length(),
                this.description.indexOf("/by")
        ).trim();
        this.by = this.description.substring(
                this.description.indexOf("/by") + "/by".length()).trim();

        if (this.task.isEmpty() | this.by.isEmpty()) {
            throw new InvalidDeadlineException();
        }
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.task + " (by: " + by + ")";
    }
}