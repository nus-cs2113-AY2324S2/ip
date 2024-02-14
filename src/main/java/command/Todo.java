package command;

public class Todo extends Task {
    protected String symbol = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "\t" + "[" + symbol + "]" + "[" + super.getStatusIcon() + "] " + description;
    }
}
