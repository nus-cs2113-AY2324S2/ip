public class Event extends Deadline {

    protected String start;

    public Event(String description, String start, String by) {
        super(description, by);
        this.start = start;
    }

    @Override
    public String toString() {
        return "[E]" + super.getListItem() + " (from: " + start + " to: " + by + ")";
    }

}
