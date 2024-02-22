package Blue;

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
    public String toSaveTextFormat() {
        String done = (getStatusIcon().equals("X") ? "1" : "0");
        return "E|" + done + "|" + this.getDescription() + "|" + this.getBy() + "|" + from + System.lineSeparator();
    }

    @Override
    public String toString() {
        return this.getDescription() + " from " + from + " to " + this.getBy();
    }
}
