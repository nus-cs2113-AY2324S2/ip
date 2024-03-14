package tasks;

public class Todo extends Task {
    /** BADGE is the symbol representing each type of subtask */
    private final static char BADGE = 'T';

    @Override
    public char getBadge() {
        return BADGE;
    }

    public Todo(String description) {
        super(description);
    }
}
