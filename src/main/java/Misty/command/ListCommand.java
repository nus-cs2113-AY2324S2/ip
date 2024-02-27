package misty.command;

import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;

public class ListCommand extends Command {
    public static final String COMMAND_STRING = "list";

    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Storage storate, UserUi userUi ) {
        taskList.listAll();
    }
}
