package lotes.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String toString() {
        return String.format("[D]%s %s",
                this.getStatusIcon(), this.description);
    }

}
