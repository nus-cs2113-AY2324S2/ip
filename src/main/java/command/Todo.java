package command;

public class Todo extends Task {
    protected String symbol = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean taskDone) {
        super(description);
        super.isDone = taskDone;
    }
    @Override
    public String toString() {
        return "\t" + "[" + symbol + "]" + "[" + super.getStatusIcon() + "] " + description;
    }
}
