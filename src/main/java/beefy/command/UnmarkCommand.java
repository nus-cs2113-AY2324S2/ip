package beefy.command;

import beefy.BeefyException;
import beefy.storage.Storage;
import beefy.task.TaskList;

import java.io.IOException;

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
    public void execute() throws BeefyException, IOException {
        if (taskId < 1 || taskId > userTasks.getNumberOfTasks())
        {
            throw new BeefyException("Can you not do math, mate?");
        }
        userTasks.unmarkTask(taskId, false);
        Storage.updateDisk(userTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
