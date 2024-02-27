package misty.command;

import misty.List;
import misty.Storage;
import misty.UserUi;
import misty.exception.IllegalListIndexException;

public class DeleteCommand extends Command {
    public static final String COMMAND_STRING = "delete";
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
