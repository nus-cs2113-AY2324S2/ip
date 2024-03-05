package bossman.command;

import bossman.exceptions.commandexceptions.InvalidFindCommandException;
import bossman.task.TaskList;

/**
 * FindCommand represents the command to find tasks containing a specific keyword.
 * It implements the Command interface.
 */
public class FindCommand implements Command{
    private TaskList userTasks;
    private String taskDescription; // Keyword to search for in task descriptions

    /**
     * Constructs a FindCommand with the provided TaskList and command arguments.
     *
     * @param userTasks the TaskList containing user's tasks
     * @param commandArgs the parameters provided with the command
     * @throws InvalidFindCommandException if the command arguments are invalid
     */
    public FindCommand(TaskList userTasks, String commandArgs) throws InvalidFindCommandException {
        if (commandArgs.isEmpty()) {
            throw new InvalidFindCommandException("Missing parameter");
        }
        this.userTasks = userTasks;
        taskDescription = commandArgs;
    }

    /**
     * Executes the FindCommand by finding tasks containing the specified keyword.
     */
    @Override
    public void execute() {
        userTasks.findTask(taskDescription);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, indicating that this command is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
