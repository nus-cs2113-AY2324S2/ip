package task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.getDescription();
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}