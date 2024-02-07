public class Todo extends Task {
    private final char badge = 'T';

    public char getBadge() {
        return badge;
    }

    public Todo(String description) {
        super(description);
    }
}
