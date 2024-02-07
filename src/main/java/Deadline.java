public class Deadline extends Task {
    private final char badge = 'D';

    public char getBadge() {
        return badge;
    }


    public Deadline(String description) {
        super(description);
    }
}
