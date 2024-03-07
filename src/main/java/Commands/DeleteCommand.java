package Commands;

import Events.Task;
import Events.TaskList;
import HikoUi.Ui;
import Storage.Store;

public class DeleteCommand extends Command {


    private Store store;



    /**
     * Constructs a Command instance with a specified command keyword and parameters.
     *
     * @param commandWord The command keyword associated with this command.
     * @param parameter The parameter(s) provided with the command.
     */
    public DeleteCommand(String commandWord, String parameter, Store store) {
        super(commandWord, parameter);
        this.store = store;
    }


    /**
     * Executes the delete command. It removes the specified task from the task list,
     * displays a confirmation message, and persists the updated task list to storage.
     * If the specified index is invalid, it displays an error message.
     *
     * @param tasks The TaskList from which the task will be deleted.
     * @param ui The UI interface for interacting with the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(parameter) - 1;
            Task taskToDelete = tasks.getTask(taskNumber); // Get the task to delete
            tasks.removeTask(taskNumber); // Remove the task from the list
            ui.showDeleteTask(taskToDelete); // Display confirmation message to the user
            store.saveTasks(); // Persist the updated task list to the storage
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMsg();
        } catch (NumberFormatException e) {
            ui.showErrorMsg();
        }
    }


    /**
     * Indicates that this command does not terminate the application.
     *
     * @return false, as DeleteCommand does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
