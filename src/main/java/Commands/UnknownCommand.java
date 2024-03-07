package Commands;

import Exceptions.HikoExceptions;
import Events.Task;
import Events.TaskList;
import Storage.Store;
import HikoUi.Ui;
public class UnknownCommand extends Command{

    /**
     * Constructs an UnknownCommand with the unrecognized command word and any parameters that were entered.
     *
     * @param commandWord The command word that was not recognized.
     * @param parameter Any additional parameters that were provided with the unrecognized command.
     */
    public UnknownCommand(String commandWord, String parameter){super(commandWord,parameter);};


    /**
     * Executes the behavior for an unknown command by displaying a default message indicating
     * the command was not recognized, providing a hint to type 'help', and then showing the help message
     * with available commands.
     *
     * @param tasks The TaskList, not used in this command but required by the execute method signature.
     * @param ui The Ui instance used for displaying messages to the user.
     */
    public void execute(TaskList tasks, Ui ui) {
        Ui.defaultmsg();
        Ui.hint();
        Ui.helpmsg();

    }

    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return false always, as UnknownCommand does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}
