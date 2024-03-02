package Event;

public class Deadline extends Task {
    public String by;
    public Deadline(String input) {
        super(input);
        String[] parts = input.split(" /by ", 2);
        this.description = parts[0].substring(9).trim();
        this.by = parts[1];
        this.eventType = "[D]";
    }
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStorageString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}