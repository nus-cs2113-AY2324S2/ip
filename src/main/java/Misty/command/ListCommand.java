package misty.command;

import misty.List;
import misty.Storage;
import misty.UserUi;

public class ListCommand extends Command {
    public static final String COMMAND_STRING = "list";

    public ListCommand() {
    }

    @Override
    public void execute(List taskList, Storage storate, UserUi userUi ) {
        taskList.listAll();
    }
}
