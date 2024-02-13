package beefy.command;

import beefy.BeefyException;
import beefy.storage.Storage;
import beefy.task.TaskList;
import beefy.task.Task;

import java.io.IOException;

public class EventCommand implements Command {
    private TaskList userTasks;
    private String taskDescription, taskFrom, taskTo;

    public EventCommand(TaskList userTasks, String userParams) throws BeefyException {
        this.userTasks = userTasks;
        String[] taskDetails = userParams.trim().split("/from|/to");
        if (taskDetails.length < 3) {
            throw new BeefyException("Use format:" + System.lineSeparator()
                    + "event (Task Description) /from (Date) /to (Date)");
        }
        taskDescription = taskDetails[0].trim();
        taskFrom = taskDetails[1].trim();
        taskTo = taskDetails[2].trim();
    }

    @Override
    public void execute() throws IOException {
        Task addedTask = userTasks.addTask(taskDescription, taskFrom, taskTo, false);
        Storage.addToDisk(addedTask.toDiskFormat());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
