package misty.command;

import java.util.regex.Pattern;
import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;

public abstract class Command {
    public static final String COMMAND_STRING = "command";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * Constructs Command object - Not used due to class being abstract.
     */
    public Command() {
    }

    /**
     * Executes command - Not used due to class being abstract.
     *
     * @param taskList ArrayList containing all tasks.
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
    public abstract void execute(TaskList taskList, Storage storage, UserUi userUi);
}