package gab.command;

import gab.task.TaskList;

public interface Command {
    void execute (String input, TaskList taskList);
}
