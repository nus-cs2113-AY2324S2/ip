package tasks;

public class Deadline extends Task {
    /** BADGE is the symbol representing each type of subtask */
    private final static char BADGE = 'D';

    @Override
    public char getBadge() {
        return BADGE;
    }


    public Deadline(String description) {
        super(description);
    }
}
