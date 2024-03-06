package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;

/**
 * Represents the command given by the user to exit the program
 */
public class ExitCommand extends Command {
    //private static boolean isExit;
    public static final String COMMAND_WORD = "bye";

    //public static final String MESSAGE_EXIT_CONFIRMATION = "Exiting Winter...See you again!";

    public ExitCommand() {
        Command.setExit(true);
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.sayBye();
    }

}
