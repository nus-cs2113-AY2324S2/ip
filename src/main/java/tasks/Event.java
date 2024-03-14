package tasks;

public class Event extends Task {
    /** BADGE is the symbol representing each type of subtask */
    private final static char BADGE = 'E';

    @Override
    public char getBadge() {
        return BADGE;
    }

    public Event(String description) {
        super(description);
    }
}
