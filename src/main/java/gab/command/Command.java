package gab.command;

import gab.task.TaskList;

public abstract class Command {
    public abstract void execute (String input, TaskList taskList);
}
