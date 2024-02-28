package misty.command;

import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;

/**
 * Lists all tasks in task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_STRING = "list";

    /**
     * Constructs ListCommand object.
     */
    public ListCommand() {
    }

    /**
     * Executes list command to list all tasks in task list.
     *
     * @param taskList ArrayList containing all tasks.
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi ) {
        taskList.listAll();
    }
}