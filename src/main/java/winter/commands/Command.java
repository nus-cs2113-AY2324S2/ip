package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;

/**
 * The parent class of all the Command type objects that can be instantiated depending on the user input
 */
public class Command {
    //BYE,LIST,MARK,UNMARK,TODO,DEADLINE,EVENT, DELETe;
    private static boolean isExit = false;
    protected Command() {

    }

    /**
     * Executes the command, depends on implementation by child classes
     * @param tasks The TaskList object representing a list of the tasks
     * @param ui The user interface that provides feedback to the user
     * @param storage The storage object which helps store changes made to the list
     */
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
