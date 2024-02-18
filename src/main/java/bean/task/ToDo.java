package bean.task;

import bean.command.exception.NoValueException;

public class ToDo extends Task {
    public ToDo(String description) throws NoValueException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toCommand() {
        return "todo " + description + " /isDone " + isDone;
    }
}

