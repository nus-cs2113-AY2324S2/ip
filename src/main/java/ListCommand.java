import java.util.ArrayList;
/**
 * Represents a command to list all tasks in a task list.
 * This command is used to display all the tasks currently stored in the task list.
 * <p>
 * The command does not require any parameters as it operates on the entire task list.
 * </p>
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks in the task list.
     * This method retrieves all tasks from the task list and displays them using the user interface.
     *
     * @param tasks    the task list from which all tasks will be listed
     * @param ui       the user interface to display the list of tasks
     * @param storage the storage, which is not used in this command
     */
    @Override
    public ArrayList<Task> execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks.retrieveAllTasks());
        return null;
    }

    /**
     * Indicates whether this command is intended to exit the application.
     *
     * @return false, as this command does not exit the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}


