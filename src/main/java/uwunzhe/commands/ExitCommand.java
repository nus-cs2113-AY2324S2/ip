package uwunzhe.commands;

import uwunzhe.util.TaskList;
import uwunzhe.handler.Storage;
import uwunzhe.exceptions.UwunzheException;
import uwunzhe.exceptions.ExceptionMessages;

public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand.
     * 
     * @param commandString The command from the user.
     * @param taskString The string appended to the command to be executed.
     */
    public ExitCommand(String commandString, String taskString) {
        super(commandString, taskString);
    }

    /**
     * Exits the bot.
     * 
     * @param taskList The list of tasks of type {@link TaskList}.
     * @param storage The storage handler of tyle {@link Storage}.
     * @throws UwunzheException If the command is invalid.
     */
    public void execute(TaskList taskList, Storage storage)
            throws UwunzheException {
        if (this.taskString.length() > 0) {
            throw new UwunzheException(ExceptionMessages.UNEXPECTED_EXTRA_COMMAND);
        }
        return;
    }

    /**
     * Returns whether the current command is an exit comamnd or not.
     * 
     * @return True if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
    
}
