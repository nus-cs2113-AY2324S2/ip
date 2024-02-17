package bob;

public class Event extends Deadline {

    protected String start;

    public Event(String description, String start, String by, boolean isDone) {
        super(description, by, isDone);
        this.start = start;
    }

    public Event(String stringRepresentation) {
        super(stringRepresentation);
        String[] split = stringRepresentation.substring(6).split(" ");
        this.description = split[0];
        this.start = split[1];
        this.by = split[2];
    }

    @Override
    public String toString() {
        return "[E]" + super.getListItem() + " (from: " + start + " to: " + by + ")";
    }

}
