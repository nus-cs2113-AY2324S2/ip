package bean.task;

import bean.command.exception.NoValueException;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) throws NoValueException {
        super(description);
        if (by == null) {
            throw new NoValueException();
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    public void setBy(String by) {
        this.by = by;
    }
}
