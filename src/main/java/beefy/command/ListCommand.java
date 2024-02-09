package beefy.command;

import beefy.BeefyException;
import beefy.task.TaskList;
import beefy.ui.Ui;

public class ListCommand implements Command{
    private TaskList userTasks;

    public ListCommand(TaskList userTasks, String userParams) throws BeefyException {
        if (!userParams.isEmpty()) {
            throw new BeefyException("Use format:" + System.lineSeparator() + "bye");
        }
        if (userTasks.getNumberOfTasks() == 0) {
            throw new BeefyException("You have no tasks u lazy bum!");
        }
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
