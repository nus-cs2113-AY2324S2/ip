package Event;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.description = description.substring(5).trim();
        this.eventType = "[T]";
    }
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
