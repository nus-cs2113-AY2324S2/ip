package misty.command;

import misty.List;
import misty.Storage;
import misty.UserUi;
import misty.exception.IllegalListIndexException;

public class MarkCommand extends Command{
    public static final String COMMAND_STRING = "mark";
    protected int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List taskList, Storage storage, UserUi userUi) {
        try {
            taskList.markTask(index);
        } catch (IllegalListIndexException e) {
            userUi.printErrorInvalidId();
        }
    };
}
