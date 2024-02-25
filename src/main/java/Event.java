public class Event extends Task {

    public Event(String description) {
        super(description);
        this.type = "E";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description;
    }
}
