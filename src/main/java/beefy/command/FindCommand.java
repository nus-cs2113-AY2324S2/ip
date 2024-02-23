package beefy.command;

import beefy.BeefyException;
import beefy.storage.Storage;
import beefy.task.Task;
import beefy.task.TaskList;

import java.io.IOException;

public class FindCommand implements Command{
    private TaskList userTasks;
    private String taskDescription;

    public FindCommand(TaskList userTasks, String userParams) throws BeefyException {
        if (userParams.isEmpty()) {
            throw new BeefyException("Quit fooling me, I do not see any task to find!");
        }
        this.userTasks = userTasks;
        taskDescription = userParams;
    }

    @Override
    public void execute() {
        userTasks.findTask(taskDescription);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
