public class Event extends Task {
    private final static char BADGE = 'E';

    public char getBadge() {
        return BADGE;
    }

    public Event(String description) {
        super(description);
    }
}
