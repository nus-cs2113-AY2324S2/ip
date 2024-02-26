package beefy.command;

import beefy.BeefyException;
import beefy.task.TaskList;
import beefy.task.Task;
import beefy.storage.Storage;

import java.io.IOException;

/**
 * Represents a command to add deadlines to task list.
 */
public class DeadlineCommand implements Command {
    private TaskList userTasks;
    private String taskDescription, taskBy;

    /**
     * Constructs a new DeadlineCommand object with user input.
     *
     * @param userTasks User list of tasks.
     * @param userParams User input of the deadline command.
     * @throws BeefyException if there is any formatting issues.
     */
    public DeadlineCommand(TaskList userTasks, String userParams) throws BeefyException {
        this.userTasks = userTasks;
        String[] taskDetails = userParams.trim().split("/by");
        if (taskDetails.length < 2) {
            throw new BeefyException("Use format:" + System.lineSeparator() + "deadline (Task Description) /by (Date)");
        }
        taskDescription = taskDetails[0].trim();
        taskBy = taskDetails[1].trim();
    }

    /**
     * Executes the deadline command, adding the deadline task into user task list, saving data in text file.
     *
     * @throws IOException if an IO error occurs.
     */
    @Override
    public void execute() throws IOException {
        Task addedTask = userTasks.addTask(taskDescription, taskBy, false);
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
