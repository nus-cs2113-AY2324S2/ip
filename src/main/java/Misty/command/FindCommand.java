package misty.command;

import misty.data.TaskList;
import misty.data.exception.EmptyParameterException;
import misty.storage.Storage;
import misty.ui.UserUi;

/**
 * Finds tasks matching a specific keyword in task list.
 */
public class FindCommand extends Command {
    public static final String COMMAND_STRING = "find";
    private String keyword;

    /**
     * Constructs FindCommand object.
     *
     * @param keyword Search term used to find tasks in task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes find command to find tasks matching keyword in task list.
     *
     * @param taskList ArrayList containing all tasks.
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.find(keyword);
        } catch (EmptyParameterException e) {
            userUi.printErrorEmptyParameter();
        }
    }
}