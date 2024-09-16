import java.util.ArrayList;

/**
 * Represents a command to exit the application.
 * This command is used to gracefully terminate the application.
 * <p>
 * The command does not require any parameters as its sole purpose is to exit the application.
 * </p>
 */

public class ExitCommand extends Command {

    /**
     * Executes the command to exit the application.
     * This method displays a line separator and the initial greeting message to the user.
     *
     * @param tasks    the task list, which is not used in this command
     * @param ui       the user interface to display messages
     * @param storage the storage, which is not used in this command
     */
    @Override
    public ArrayList<Task> execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.printHello();
        return null;
    }

    /**
     * Indicates whether this command is intended to exit the application.
     *
     * @return true, as this command is specifically designed to exit the application
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

