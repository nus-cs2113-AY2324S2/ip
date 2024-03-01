package Nick.command;

import Nick.task.TaskList;
import Nick.ui.Ui;
import Nick.storage.Storage;

/**
 * Represents the Bye command which inherits from the Command class.
 * It includes the execute method which calls the ui printByeMsg method and returns a true when isExit method is called.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command with just the ui object.
     *
     * @param tasks ArrayList of Task objects.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMsg();
    }

    /**
     * Returns the boolean to exiting the program.
     *
     * @return Boolean of true which represents end of program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
