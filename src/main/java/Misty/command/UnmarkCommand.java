package misty.command;

import misty.List;
import misty.Storage;
import misty.UserUi;
import misty.exception.IllegalListIndexException;

import java.util.regex.Pattern;

public class UnmarkCommand extends Command{
    public static final String COMMAND_STRING = "unmark";
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<index>\\d+)");
    protected int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List taskList, Storage storage, UserUi userUi) {
        try {
            taskList.unmarkTask(index);
        } catch (IllegalListIndexException e) {
            userUi.printErrorInvalidId();
        }
    };
}
