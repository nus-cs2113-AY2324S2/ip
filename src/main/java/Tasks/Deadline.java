package Tasks;

import Tasks.Task;

public class Deadline extends Task {
    private final static char BADGE = 'D';

    public char getBadge() {
        return BADGE;
    }


    public Deadline(String description) {
        super(description);
    }
}
