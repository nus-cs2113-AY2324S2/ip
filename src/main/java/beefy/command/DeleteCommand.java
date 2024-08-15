package beefy.command;

import beefy.BeefyException;
import beefy.storage.Storage;
import beefy.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to delete tasks from task list.
 */
public class DeleteCommand implements Command {
    private TaskList userTasks;
    private int taskId;

    /**
     * Constructs a new DeleteCommand object with user input.
     *
     * @param userTasks User list of tasks.
     * @param userParams User input of the deadline command.
     * @throws BeefyException if there is any formatting issues.
     */
    public DeleteCommand(TaskList userTasks, String userParams) throws BeefyException {
        this.userTasks = userTasks;
        try {
            taskId = Integer.parseInt(userParams);
        } catch (NumberFormatException e) {
            throw new BeefyException("Use format:" + System.lineSeparator() + "delete (taskId)");
        }
    }

    /**
     * Executes the deadline command, deleting the deadline task from user task list, saving data in text file.
     *
     * @throws BeefyException if there is any formatting issues.
     */
    @Override
    public void execute() throws BeefyException, IOException {
        if (taskId < 1 || taskId > userTasks.getNumberOfTasks()) {
            throw new BeefyException("Can you not do math, mate?");
        }
        userTasks.deleteTask(taskId);
        Storage.updateDisk(userTasks);
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
