package misty.command;

import misty.List;
import misty.Storage;
import misty.UserUi;

import java.util.regex.Pattern;
public abstract class Command {
    public static final String COMMAND_STRING = "command";

    public Command() {
    }

    public abstract void execute(List taskList, Storage storage, UserUi userUi);

}
