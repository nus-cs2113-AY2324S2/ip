package uwunzhe.commands;

import uwunzhe.util.TaskList;
import uwunzhe.util.Ui;
import uwunzhe.handler.Storage;
import uwunzhe.exceptions.UwunzheException;

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
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage handler.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws UwunzheException {
        if (this.taskString.length() > 0) {
            throw new UwunzheException("Extra... Value?!");
        }
        return;
    }

    /**
     * Checks if the command is an exit command.
     * 
     * @return True if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
    
}
