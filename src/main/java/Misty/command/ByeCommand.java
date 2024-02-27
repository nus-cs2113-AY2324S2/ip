package misty.command;

import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;

public class ByeCommand extends Command {
    public static final String COMMAND_STRING = "bye";

    public ByeCommand() {
    }

    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        userUi.printByeMessage();
        userUi.printMessageBorder();
        System.exit(0);
    };
}
