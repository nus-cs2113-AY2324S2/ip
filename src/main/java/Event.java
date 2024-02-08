public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    public void from() {

    }

    public void to() {

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + description + "(from:";
    }
}
