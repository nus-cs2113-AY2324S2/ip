package misty.command;

import java.util.regex.Pattern;
import misty.data.TaskList;
import misty.data.exception.IllegalListIndexException;
import misty.storage.Storage;
import misty.ui.UserUi;

/**
 * Removes a task from task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_STRING = "delete";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("(?<index>\\d+)");
    private int index;

    /**
     * Constructs DeleteCommand object.
     *
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes delete command to remove a task from task list.
     *
     * @param taskList ArrayList containing all tasks.
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.deleteTask(index);
        } catch (IllegalListIndexException e) {
            userUi.printErrorInvalidId();
        }
    };
}