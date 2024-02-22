package task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getTaskAsString() {
        return "T | " + getDoneAsInteger() + " | " + description;
    }
}
