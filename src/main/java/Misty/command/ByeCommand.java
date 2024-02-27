package misty.command;

import misty.List;
import misty.Storage;
import misty.UserUi;

public class ByeCommand extends Command {
    public static final String COMMAND_STRING = "bye";

    public ByeCommand() {
    }

    public void execute(List taskList, Storage storage, UserUi userUi) {
        userUi.printByeMessage();
        userUi.printMessageBorder();
        System.exit(0);
    };
}
