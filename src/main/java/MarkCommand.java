import java.util.ArrayList;

/**
 * Represents a command to mark a task as done in a task list.
 * This command is used to change the status of a specified task to completed.
 * <p>
 * The task number is used to identify the task to be marked as done.
 * </p>
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a MarkCommand with the given task number.
     * The task number is used to identify the task to be marked as done.
     *
     * @param taskNumber the index of the task to be marked as done
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark a task as done in the task list.
     * If the task number is valid and a task exists at that index, the task's status is updated to done.
     * The user interface is then updated to reflect the change.
     *
     * @param tasks    the task list from which the task will be marked as done
     * @param ui       the user interface to display messages
     * @param storage the storage to save the updated task list
     * @throws DukeException if the task number is invalid or does not correspond to a task in the list
     */
    @Override
    public ArrayList<Task> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.fetchTaskByIndex(taskNumber);
            task.markAsDone();
            ui.showLine();
            ui.printTasksMarked(task, tasks.retrieveAllTasks());
        } else {
            throw new DukeException("Invalid task number!");
        }
        return null;
    }

    /**
     * Indicates whether this command is intended to exit the application.
     *
     * @return false, as this command does not exit the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}


