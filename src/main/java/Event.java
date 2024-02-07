public class Event extends Deadline {
    private String from;

    private String getFrom() {
        return from;
    }

    private void setFrom(String from) {
        this.from = from;
    }

    public Event(String description, String from, String to) {
        super(description, to);
        this.from = from;
    }

    @Override
    public String toString() {
        return this.getDescription() + " from " + from + " to " + this.getBy();
    }
}