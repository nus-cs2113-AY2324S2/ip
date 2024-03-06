package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Task;
import helpy.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to exit from the chatbot
 */
public class ExitCommand extends Command{
    /**
     * Constructs a new ExitCommand object with an exit status of true.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Executes the ExitCommand by displaying a goodbye message to the user.
     *
     * @param taskList The task list containing the user's tasks.
     * @param ui       The user interface for displaying output and messages.
     * @param storage  The storage system for managing task data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayGoodbye();
    }
}
