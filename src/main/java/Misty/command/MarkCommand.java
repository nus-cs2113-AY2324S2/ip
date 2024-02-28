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

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.markTask(index);
        } catch (IllegalListIndexException e) {
            userUi.printErrorInvalidId();
        }
    };
}