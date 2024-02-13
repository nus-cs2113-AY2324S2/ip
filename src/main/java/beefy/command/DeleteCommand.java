package beefy.command;

import beefy.BeefyException;
import beefy.task.TaskList;

public class DeleteCommand implements Command {
    private TaskList userTasks;
    private int taskId;

    public DeleteCommand(TaskList userTasks, String userParams) throws BeefyException {
        this.userTasks = userTasks;
        try {
            taskId = Integer.parseInt(userParams);
        } catch (NumberFormatException e) {
            throw new BeefyException("Use format:" + System.lineSeparator() + "delete (taskId)");
        }
    }

    @Override
    public void execute() throws BeefyException{
        if (taskId < 1 || taskId > userTasks.getNumberOfTasks())
        {
            throw new BeefyException("Can you not do math, mate?");
        }
        userTasks.deleteTask(taskId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
