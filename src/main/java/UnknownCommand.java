import java.util.ArrayList;

/**
 * Represents a command to handle unknown or unsupported commands in the application.
 * This command is used when the user enters a command that does not match any of the supported commands.
 * <p>
 * The command string is used to identify the unknown command.
 * </p>
 */

public class UnknownCommand extends Command {
    private String command;

    /**
     * Constructs an UnknownCommand with the given command string.
     * The command string is used to identify the unknown command entered by the user.
     *
     * @param command the command string that does not match any known commands
     */
    public UnknownCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the command to display an error message indicating that the command is unknown.
     * This method uses the user interface to display an error message with the unknown command.
     *
     * @param tasks    the task list, which is not used in this command
     * @param ui       the user interface to display error messages
     * @param storage the storage, which is not used in this command
     */
    @Override
    public ArrayList<Task> execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printError("Unknown command: " + command);
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



