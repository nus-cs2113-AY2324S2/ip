package misty.command;

import misty.List;
import misty.Storage;
import misty.UserUi;
import misty.exception.IllegalListIndexException;

import java.util.regex.Pattern;

public class DeleteCommand extends Command {
    public static final String COMMAND_STRING = "delete";
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<index>\\d+)");
    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List taskList, Storage storage, UserUi userUi) {
        try {
            taskList.deleteTask(index);
        } catch (IllegalListIndexException e) {
            userUi.printErrorInvalidId();
        }
    };
}
