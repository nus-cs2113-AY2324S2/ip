package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.TaskList;

/**
 * Represents an unknown command entered by the user.
 */
public class UnknownCommand extends Command{
    private String fullCommand = "";

    /**
     * Constructs a new UnknownCommand object with the given full command string.
     *
     * @param fullCommand The full string of the unknown command.
     */
    public UnknownCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the unknown command by displaying an error message to the user.
     *
     * @param taskList The task list on which the command should operate.
     * @param ui       The user interface for displaying output and messages.
     * @param storage  The storage system for managing task data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showUnknownCommandErr(fullCommand);
    }
}
