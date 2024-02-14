package Tasks;

import Tasks.Task;

public class Todo extends Task {
    private final static char BADGE = 'T';

    public char getBadge() {
        return BADGE;
    }

    public Todo(String description) {
        super(description);
    }
}
