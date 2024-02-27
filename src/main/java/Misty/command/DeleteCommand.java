package misty.command;

import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;
import misty.data.exception.IllegalListIndexException;
import java.util.regex.Pattern;

public class DeleteCommand extends Command {
    public static final String COMMAND_STRING = "delete";
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<index>\\d+)");
    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.deleteTask(index);
        } catch (IllegalListIndexException e) {
            userUi.printErrorInvalidId();
        }
    };
}
