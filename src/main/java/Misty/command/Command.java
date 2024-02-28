package misty.command;

import java.util.regex.Pattern;
import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;

public abstract class Command {
    public static final String COMMAND_STRING = "command";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    public Command() {
    }

    public abstract void execute(TaskList taskList, Storage storage, UserUi userUi);
}