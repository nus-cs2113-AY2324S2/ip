package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Task;
import helpy.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to be executed in the application.
 */
public class Command {
    protected String commandBody = "";
    protected boolean isExit = false;

    /**
     * Constructs a new Command object with an empty command body.
     */
    public Command() {}

    /**
     * Constructs a new Command object with the specified command body.
     *
     * @param body The body or content of the command.
     */
    public Command(String body) {
        commandBody = body;
    }

    /**
     * Checks whether this command is an exit command.
     *
     * @return true if this command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param taskList The task list on which the command should operate.
     * @param ui       The user interface for displaying output and messages.
     * @param storage  The storage system for managing task data.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {}
}
