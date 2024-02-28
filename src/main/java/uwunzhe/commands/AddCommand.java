package uwunzhe.commands;

import uwunzhe.util.TaskList;
import uwunzhe.handler.Storage;
import uwunzhe.exceptions.UwunzheException;

public class AddCommand extends Command {
    /**
     * Constructor for AddCommand.
     * 
     * @param commandString The command from the user.
     * @param taskString The string appended to the command to be executed.
     */
    public AddCommand(String commandString, String taskString) {
        super(commandString, taskString);
    }

    /**
     * Adds a task to the list.
     * 
     * @param taskList The list of tasks of type {@link TaskList}.
     * @param storage The storage handler of tyle {@link Storage}.
     * @throws UwunzheException If the command is invalid.
     */
    public void execute(TaskList taskList, Storage storage)
            throws UwunzheException {
        taskList.addItem(this.commandString, this.taskString);
        storage.saveData(taskList);
    }
}
