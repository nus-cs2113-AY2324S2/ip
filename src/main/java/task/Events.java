package task;

/**
 * The Event class represents a task that starts at a specific time and ends at a specific time.
 */
public class Events extends Tasks{
    private String start;
    private String end;

    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSave() {
        return "E / " + super.toSave() + " / " + start + " / " + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + " to: " + end + ")";
    }
}
