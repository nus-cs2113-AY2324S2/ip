package misty.command;

import misty.List;
import misty.Storage;
import misty.UserUi;

import java.util.regex.Pattern;
public abstract class Command {
    public static final String COMMAND_STRING = "command";
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    public Command() {
    }

    public abstract void execute(List taskList, Storage storage, UserUi userUi);

}
