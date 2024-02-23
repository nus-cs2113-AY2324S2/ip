package beefy.command;

import beefy.BeefyException;
import beefy.storage.Storage;
import beefy.task.TaskList;
import beefy.task.Task;

import java.io.IOException;

public class ToDoCommand implements Command{
    private TaskList userTasks;
    private String taskDescription;

    public ToDoCommand(TaskList userTasks, String userParams) throws BeefyException {
        if (userParams.isEmpty()) {
            throw new BeefyException("Quit fooling me, I do not see any task to add!");
        }
        this.userTasks = userTasks;
        taskDescription = userParams;
    }

    @Override
    public void execute() throws IOException {
        Task addedTask = userTasks.addTask(taskDescription, false);
        Storage.addToDisk(addedTask.toDiskFormat());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
