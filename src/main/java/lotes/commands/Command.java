package lotes.commands;

import lotes.task.TaskList;

public abstract class Command {

    protected TaskList taskList;
    protected String description;

    public void execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
