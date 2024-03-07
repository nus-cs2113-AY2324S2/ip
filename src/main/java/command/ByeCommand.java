package command;

import storage.Storage;
import tasktype.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the JingHao chatbot.
 */
public class ByeCommand implements Command{

    /**
     * Executes the bye command by displaying the farewell message.
     *
     * @param taskList The list of task in the JingHao chatbot.
     * @param ui The user interface of the JingHao chatbot.
     * @param storage The file storage of the JingHao chatbot.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.farewell();
    }

    /**
     * Determines whether the command is an exit command.
     *
     * @return Returns true since this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
