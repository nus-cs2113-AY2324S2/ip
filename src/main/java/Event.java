public class Event extends Task {

    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }
}