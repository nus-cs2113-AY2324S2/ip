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