package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;

public class Command {
    //BYE,LIST,MARK,UNMARK,TODO,DEADLINE,EVENT, DELETe;
    private static boolean isExit = false;
    protected Command() {

    }
    public void execute(TaskList tasks, UI ui, Storage storage) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes.");
    };

    public CommandResult execute() {

        throw new UnsupportedOperationException("This method is to be implemented by child classes.");
    };

    public boolean isExit() {
        return isExit;
    }

    public static void setExit(boolean isExit) {
        Command.isExit = isExit;
    }



}
