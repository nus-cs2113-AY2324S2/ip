package duke.command;

import duke.task.TaskList;

public abstract class Command {
    public abstract void execute(String input, TaskList taskList);
}
