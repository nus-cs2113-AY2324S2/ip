public class Deadline extends Task {
    private final static char badge = 'D';

    public char getBadge() {
        return badge;
    }


    public Deadline(String description) {
        super(description);
    }
}
