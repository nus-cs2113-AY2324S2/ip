package misty.command;

import java.util.regex.Pattern;
import misty.data.TaskList;
import misty.data.exception.IllegalListIndexException;
import misty.storage.Storage;
import misty.ui.UserUi;

/**
 * Unmarks a task from task list.
 */
public class UnmarkCommand extends Command{
    public static final String COMMAND_STRING = "unmark";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("(?<index>\\d+)");
    protected int index;

    /**
     * Constructs UnmarkCommand object.
     *
     * @param index Index of task to be unmarked in task list.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes unmark command to unmark a task in task list.
     *
     * @param taskList TaskList object containing all tasks.
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.unmarkTask(index);
        } catch (IllegalListIndexException e) {
            userUi.printErrorInvalidId();
        }
    };
}