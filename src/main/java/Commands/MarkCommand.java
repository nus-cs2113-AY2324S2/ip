package Commands;

import Exceptions.HikoExceptions;
import Events.Task;
import Events.TaskList;
import Storage.Store;
import HikoUi.Ui;

public class MarkCommand extends Command{

    private Store store;

    /**
     * Constructs a MarkCommand with the specified parameters and a reference to the Store.
     *
     * @param commandWord The command word associated with this command, expected to be "mark".
     * @param parameter The parameter passed with the command, expected to be the task number to mark as done.
     * @param store The Store object used for saving changes to the task list.
     */
    public MarkCommand(String commandWord, String parameter, Store store) {
        super(commandWord,parameter);
        this.store = store;
    };



    /**
     * Executes the command to mark a specific task as done based on the provided task number.
     * Updates the task's status to completed and saves the updated task list to the store.
     * If the specified task number is invalid or does not exist, it will catch and handle exceptions by notifying the user.
     *
     * @param tasks The TaskList containing the tasks to be modified.
     * @param ui The Ui object used for interacting with the user.
     */
    public void execute(TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(parameter) - 1;
            if (commandWord.equals("mark")) {
                tasks.getTask(taskNumber).markAsDone();
                System.out.println("Got it! I've marked this task as done:");
            }
            System.out.println(tasks.getTask(taskNumber).toString());
            store.saveTasks();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("No such Task in the List");
        } catch (NumberFormatException e) {
            System.out.println("Please input correct Task Number");
        }
    }


    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return false always, as MarkCommand does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}
