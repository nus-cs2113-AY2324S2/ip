package Tasks;

import Tasks.Task;

public class Event extends Task {
    private final static char BADGE = 'E';

    @Override
    public char getBadge() {
        return BADGE;
    }

    public Event(String description) {
        super(description);
    }
}
