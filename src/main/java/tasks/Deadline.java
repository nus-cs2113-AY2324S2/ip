package tasks;

import baymax.Task;

public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
        this.type = "D";

    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    @Override
    public String toAddString() {
        return "[D]" + " [ ] " + description;
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description;
    }

}
