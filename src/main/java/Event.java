public class Event extends Task {
    private final char badge = 'E';

    public char getBadge() {
        return badge;
    }

    public Event(String description) {
        super(description);
    }
}
