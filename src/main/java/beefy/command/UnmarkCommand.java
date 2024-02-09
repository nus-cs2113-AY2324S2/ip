package beefy.command;

import beefy.BeefyException;
import beefy.task.TaskList;
import beefy.ui.Ui;

public class UnmarkCommand implements Command {
    private TaskList userTasks;
    private int taskId;

    public UnmarkCommand(TaskList userTasks, String userParams) throws BeefyException{
        this.userTasks = userTasks;
        try {
            taskId = Integer.parseInt(userParams);
        } catch (NumberFormatException e) {
            throw new BeefyException("Use format:" + System.lineSeparator() + "mark (taskId)");
        }
    }

    @Override
    public void execute() throws BeefyException {
        if (taskId < 1 || taskId > userTasks.getNumberOfTasks())
        {
            throw new BeefyException("Can you not do math, mate?");
        }
        userTasks.unmarkTask(taskId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
