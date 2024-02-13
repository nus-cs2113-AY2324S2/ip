package beefy.command;

import beefy.BeefyException;
import beefy.task.TaskList;
import beefy.task.Task;
import beefy.storage.Storage;

import java.io.IOException;

public class DeadlineCommand implements Command {
    private TaskList userTasks;
    private String taskDescription, taskBy;

    public DeadlineCommand(TaskList userTasks, String userParams) throws BeefyException {
        this.userTasks = userTasks;
        String[] taskDetails = userParams.trim().split("/by");
        if (taskDetails.length < 2) {
            throw new BeefyException("Use format:" + System.lineSeparator() + "deadline (Task Description) /by (Date)");
        }
        taskDescription = taskDetails[0].trim();
        taskBy = taskDetails[1].trim();
    }

    @Override
    public void execute() throws IOException {
        Task addedTask = userTasks.addTask(taskDescription, taskBy, false);
        Storage.addToDisk(addedTask.toDiskFormat());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
