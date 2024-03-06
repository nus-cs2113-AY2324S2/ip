package lotes.commands;

import lotes.task.TaskList;

public abstract class Command {

    protected TaskList taskList;
    protected String description;

    public abstract void execute();
}
