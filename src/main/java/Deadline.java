public class Deadline extends Task {

    protected String task;
    protected String by;

    public Deadline(String description) {
        super(description);
        this.task = this.description.substring(
                "Deadline".length(), 
                this.description.indexOf("/by")
                ).trim();
        this.by = this.description.substring(
                this.description.indexOf("/by") + "/by".length()).trim();
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.task + " (by: " + by + ")";
    }
}
