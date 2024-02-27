package misty.command;

import misty.List;
import misty.Storage;
import misty.UserUi;
import misty.exception.IllegalListIndexException;

public class UnmarkCommand extends Command{
    public static final String COMMAND_STRING = "unmark";
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
