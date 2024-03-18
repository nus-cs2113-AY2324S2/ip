package Commands;

import Events.*;
import HikoUi.Ui;
import Storage.Store;
public class ClearCommand extends Command{

    private Store store;



    /**
     * Constructs a ClearCommand with the specified command word, parameter, and store reference.
     * The command word and parameter are used by the superclass. The store is used for data persistence.
     *
     * @param commandWord The command word associated with this command, typically "clear".
     * @param parameter The parameter(s) provided with the command. Not used in this command but required for compatibility with the superclass.
     * @param store The Store object used for persisting changes to the task list.
     */
    public ClearCommand(String commandWord, String parameter, Store store) {
        super(commandWord, parameter);
        this.store = store;
    }



    /**
     * Executes the clear command by removing all tasks from the task list.
     * It then saves the updated (empty) task list to persistent storage and
     * displays a message to the user confirming that the tasks have been cleared.
     *
     * @param tasks The TaskList from which all tasks will be cleared.
     * @param ui The Ui instance used for interacting with the user and displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            tasks.clearAllTasks();
            store.saveTasks(); // Persist the updated task list to the storage
            Ui.showClearMsg();
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMsg();
        } catch (NumberFormatException e) {
            ui.showErrorMsg();
        }
    }



    /**
     * Indicates whether executing this command should terminate the application.
     *
     * @return false always, as the clear command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }


}
