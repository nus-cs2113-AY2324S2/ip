package kobot.task;

public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String date, Boolean isDone) {
        super(description, isDone);
        this.by = date;
    }
    public Deadline(String description, String date) {
        super(description);
        this.by = date;
    }

    public String toString() {
        return String.format("[D]%s %s (by: %s)", this.getStatusIcon(), this.description, this.by);
    }
    
    public String toStorageFormat() {
        return String.format("D;%b;%s;%s", this.isDone, this.description, this.by);
    }
}
