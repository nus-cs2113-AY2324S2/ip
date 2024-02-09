package beefy.command;

import beefy.task.TaskList;

public class ListCommand implements Command{
    private TaskList userTasks;
    public ListCommand(TaskList userTasks) {
        this.userTasks = userTasks;
    }

    @Override
    public void execute() {
        userTasks.listOut();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
