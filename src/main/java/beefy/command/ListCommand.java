package beefy.command;

import beefy.BeefyException;
import beefy.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to list all tasks in task list.
 */
public class ListCommand implements Command{
    private TaskList userTasks;

    /**
     * Constructs a new ListCommand object with user input.
     *
     * @param userTasks User list of tasks.
     * @param userParams User input of the deadline command.
     * @throws BeefyException if there is any formatting issues.
     */
    public ListCommand(TaskList userTasks, String userParams) throws BeefyException {
        if (!userParams.isEmpty()) {
            throw new BeefyException("Use format:" + System.lineSeparator() + "bye");
        }
        if (userTasks.getNumberOfTasks() == 0) {
            throw new BeefyException("You have no tasks u lazy bum!");
        }
        this.userTasks = userTasks;
    }

    /**
     * Executes the list command, listing all tasks in user task list.
     */
    @Override
    public void execute() {
        userTasks.listOut();
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
