package Commands;

import Exceptions.HikoExceptions;
import Events.Task;
import Events.TaskList;
import Storage.Store;
import HikoUi.Ui;

public class ExceptionCommand extends Command{



    /**
     * Constructs an ExceptionCommand with a specific command word and any associated parameters.
     * This constructor allows for the initialization of the command with details about the
     * exceptional situation or the unrecognized command.
     *
     * @param commandWord The command word associated with the exceptional command, typically indicating the type of exception or error.
     * @param Parameter The parameter(s) associated with the command, if any, providing additional details about the exceptional situation.
     */
    public ExceptionCommand(String commandWord, String Parameter) {
        super(commandWord,Parameter);
    }

    /**
     * Executes the exception handling behavior of this command. Typically, it notifies the user
     * that an unrecognized or improper command was entered by displaying a default message.
     * Additional suggestions or help information could also be provided to the user.
     *
     * @param tasks The TaskList, not directly used by this command but required by the execute method signature.
     * @param ui The Ui instance used for interacting with the user, displaying messages or instructions.
     */
    public void execute(TaskList tasks, Ui ui) {
        Ui.defaultmsg();
    }


    /**
     * Indicates whether this command should cause the application to exit. For ExceptionCommand,
     * it typically does not cause the application to exit but rather provides feedback about an error or unexpected situation.
     *
     * @return false always, as ExceptionCommand does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }


}
