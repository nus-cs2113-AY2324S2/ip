package Commands;

import Events.Task;
import Exceptions.HikoExceptions;
import Events.TaskList;
import HikoUi.Ui;
import Storage.Store;

public abstract class Command {

    protected String commandWord;
    protected String parameter;


    /**
     * Constructs a Command instance with a specified command word and parameters.
     *
     * @param commandWord The command word that identifies the type of command.
     * @param parameter The additional information or parameters required to execute the command.
     */
    public Command(String commandWord, String parameter) {
        this.commandWord = commandWord;
        this.parameter = parameter;
    }


    /**
     * Executes the command. This method should be implemented by concrete command classes
     * to define specific behaviors when the command is executed. Depending on the command,
     * this may involve manipulating tasks, interacting with the user, or altering application state.
     *
     * @param tasks The TaskList containing all tasks managed by the application.
     * @param ui The Ui instance used for any required interaction with the user.
     */

    public void execute (TaskList tasks, Ui ui) {
    }

    /**
     * Determines if the execution of this command should signal the application to exit.
     * This method must be implemented by concrete command classes to indicate whether
     * their execution results in the application terminating.
     *
     * @return true if executing this command should terminate the application; false otherwise.
     */
    public abstract boolean isExit();


}
