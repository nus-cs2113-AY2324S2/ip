package beefy.command;

import beefy.BeefyException;
import beefy.storage.Storage;
import beefy.task.TaskList;
import beefy.task.Task;

import java.io.IOException;

/**
 * Represents a command to add a todo task in task list.
 */
public class ToDoCommand implements Command{
    private TaskList userTasks;
    private String taskDescription;

    /**
     * Constructs a new ToDoCommand object with user input.
     *
     * @param userTasks User list of tasks.
     * @param userParams User input of the deadline command.
     * @throws BeefyException if there is any formatting issues.
     */
    public ToDoCommand(TaskList userTasks, String userParams) throws BeefyException {
        if (userParams.isEmpty()) {
            throw new BeefyException("Quit fooling me, I do not see any task to add!");
        }
        this.userTasks = userTasks;
        taskDescription = userParams;
    }
    /**
     * Executes the todo command, adding a todo task into user task list, saving data in text file.
     *
     * @throws IOException if an IO error occurs.
     */
    @Override
    public void execute() throws IOException {
        Task addedTask = userTasks.addTask(taskDescription, false);
        Storage.addToDisk(addedTask.toDiskFormat());
    }

    /**
     * Indicates whether this is an exit command.
     * Returns false since this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
