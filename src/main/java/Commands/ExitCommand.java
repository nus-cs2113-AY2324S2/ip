package Commands;

import Events.TaskList;
import HikoUi.Ui;
import Storage.Store;

public class ExitCommand extends Command {

    private Store store;

    /**
     * Constructs an ExitCommand with the specified command word, parameter,
     * and a reference to the Store for persisting changes.
     *
     * @param commandWord The command word associated with this command, typically "exit".
     * @param parameter Any parameters that come with the command. Typically unused for exit commands.
     * @param store The Store object used for saving changes to the task list before exiting.
     */
    public ExitCommand(String commandWord, String parameter, Store store) {
        super(commandWord, parameter);
        this.store = store;// Ensure this matches the Command constructor.
    }


    /**
     * Executes the exit command. This method saves all current tasks to the store
     * and then signals the application to close by displaying a goodbye message to the user.
     *
     * @param tasks The TaskList containing the tasks to be saved. Not directly used in this command
     *              but required by the execute method signature.
     * @param ui The Ui instance used for interacting with the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        store.saveTasks(); // Saves tasks before exiting.
        ui.sayGoodbye(); // Informs the user that the application is exiting.
    }



    /**
     * Indicates whether this command should trigger the application to exit.
     *
     * @return true always, indicating that this command results in application termination.
     */
    @Override
    public boolean isExit() {
        return true; // Properly overrides isExit to indicate this command exits the application.
    }
}