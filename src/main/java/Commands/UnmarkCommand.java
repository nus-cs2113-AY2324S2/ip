package Commands;


import Exceptions.HikoExceptions;
import Events.Task;
import Events.TaskList;
import Storage.Store;
import HikoUi.Ui;
public class UnmarkCommand extends Command {


    private Store store;

    /**
     * Constructs an UnmarkCommand with a specified command word, parameter indicating the task number,
     * and a reference to the Store for persisting changes.
     *
     * @param commandWord The command word associated with this command, typically "unmark".
     * @param parameter The parameter passed with the command, expected to be the task number to unmark.
     * @param store The Store object used for saving changes to the task list.
     */
    public UnmarkCommand(String commandWord, String parameter,Store store){
        super(commandWord,parameter);
        this.store = store;
    };


    /**
     * Executes the unmark command. It tries to unmark the specified task as not completed based on the task number provided.
     * After unmarking the task, it saves the updated task list to the store. If the specified task number is invalid
     * or does not exist, it will catch and handle the exception by notifying the user.
     *
     * @param tasks The TaskList containing the tasks to be modified.
     * @param ui The Ui object used for interacting with the user.
     */
    public void execute(TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(parameter) - 1;
            if (commandWord.equals("mark")) {
                tasks.getTask(taskNumber).unmarkAsDone();
                System.out.println("Got it! I've marked this task as not done yet:");
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
     * @return false always, as UnmarkCommand does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }


}
