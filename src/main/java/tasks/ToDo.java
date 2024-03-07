package tasks;

import baymax.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toAddString() {
        return "[E]" + " [ ] " + description;
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

}

