package fredbot.task;

import fredbot.exception.EmptyDescriptionException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws EmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String saveString() {
        return "D" + super.saveString() + " | " + by;
    }
}
