public class Todo extends Task {
    private final static char badge = 'T';

    public char getBadge() {
        return badge;
    }

    public Todo(String description) {
        super(description);
    }
}
