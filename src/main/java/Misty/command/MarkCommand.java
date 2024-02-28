package misty.command;

import java.util.regex.Pattern;
import misty.data.TaskList;
import misty.data.exception.IllegalListIndexException;
import misty.storage.Storage;
import misty.ui.UserUi;

public class MarkCommand extends Command{
    public static final String COMMAND_STRING = "mark";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("(?<index>\\d+)");
    private int index;

    /**
     * Constructs MarkCommand object.
     *
     * @param index Index of task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes mark command to mark a task as done in task list.
     *
     * @param taskList ArrayList containing all tasks.
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.markTask(index);
        } catch (IllegalListIndexException e) {
            userUi.printErrorInvalidId();
        }
    };
}