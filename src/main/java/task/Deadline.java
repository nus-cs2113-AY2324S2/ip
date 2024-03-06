package task;

import ui.Time;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = Time.standardize(by);
        } catch (IllegalArgumentException e) {
            this.by = by;
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
