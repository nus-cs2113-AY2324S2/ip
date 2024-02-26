package beefy.command;

import beefy.BeefyException;
import beefy.storage.Storage;
import beefy.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to unmark a task in task list.
 */
public class UnmarkCommand implements Command {
    private TaskList userTasks;
    private int taskId;

    /**
     * Constructs a new UnmarkCommand object with user input.
     *
     * @param userTasks User list of tasks.
     * @param userParams User input of the deadline command.
     * @throws BeefyException if there is any formatting issues.
     */
    public UnmarkCommand(TaskList userTasks, String userParams) throws BeefyException{
        this.userTasks = userTasks;
        try {
            taskId = Integer.parseInt(userParams);
        } catch (NumberFormatException e) {
            throw new BeefyException("Use format:" + System.lineSeparator() + "mark (taskId)");
        }
    }

    /**
     * Executes the unmark command, marking the task in user task list as not done, saving data in text file.
     * @throws IOException if an IO error occurs.
     */
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
