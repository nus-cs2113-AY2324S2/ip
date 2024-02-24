package bossman.command;

import bossman.task.TaskList;

/**
 * ListCommand represents the command to list all tasks.
 * It implements the Command interface.
 */
public class ListCommand implements Command {
    private TaskList userTasks;

    /**
     * Constructs a ListCommand with the provided TaskList.
     *
     * @param userTasks the TaskList containing user's tasks
     */
    public ListCommand(TaskList userTasks) {
        this.userTasks = userTasks;
    }

    /**
     * Executes the ListCommand by printing all tasks.
     */
    @Override
    public void execute() {
        userTasks.printTasks();
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
