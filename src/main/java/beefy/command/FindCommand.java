package beefy.command;

import beefy.BeefyException;
import beefy.storage.Storage;
import beefy.task.Task;
import beefy.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to find tasks in task list.
 */
public class FindCommand implements Command{
    private TaskList userTasks;
    private String taskDescription;

    /**
     * Constructs a new FindCommand object with user input.
     *
     * @param userTasks User list of tasks.
     * @param userParams User input of the deadline command.
     * @throws BeefyException if there is any formatting issues.
     */
    public FindCommand(TaskList userTasks, String userParams) throws BeefyException {
        if (userParams.isEmpty()) {
            throw new BeefyException("Quit fooling me, I do not see any task to find!");
        }
        this.userTasks = userTasks;
        taskDescription = userParams;
    }

    /**
     * Executes the find command, finding all related tasks.
     */
    @Override
    public void execute() {
        userTasks.findTask(taskDescription);
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
