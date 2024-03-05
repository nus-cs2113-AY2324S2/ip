package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;

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
