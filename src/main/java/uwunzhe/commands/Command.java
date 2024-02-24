package uwunzhe.commands;

import uwunzhe.util.TaskList;
import uwunzhe.handler.Storage;
import uwunzhe.exceptions.UwunzheException;
public abstract class Command {
    protected String commandString;
    protected String taskString;

    /**
     * Constructor for Command.
     * 
     * @param commandString The command from the user.
     * @param taskString The string appended to the command to be executed.
     */
    public Command(String commandString, String taskString) {
        this.commandString = commandString;
        this.taskString = taskString;
    }

    /**
     * Executes the command based on user input.
     * 
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     * @return True if the list has been updated, false otherwise.
     * @throws UwunzheException If the command is invalid.
     */
    public abstract void execute(TaskList taskList, Storage storage)
            throws UwunzheException;

    /**
     * Checks if the command is an exit command.
     * 
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}