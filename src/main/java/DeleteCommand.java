/**
 * Represents a command to delete a task from a task list by its index.
 * This command is used to remove a specified task from the list based on its position.
 * <p>
 * The task number is used to identify the task to be deleted.
 * </p>
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand with the given task number.
     * The task number is used to identify the task to be deleted from the task list.
     *
     * @param taskNumber the index of the task to be deleted
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to delete a task from the task list by its index.
     * If the task number is valid and a task exists at that index, the task is removed.
     * The user interface is then updated to reflect the removal of the task.
     *
     * @param tasks    the task list from which the task will be deleted
     * @param ui       the user interface to display messages
     * @param storage the storage to save the updated task list
     * @throws DukeException if the task number is invalid or does not correspond to a task in the list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.fetchTaskByIndex(taskNumber);
        if (task != null) {
            tasks.removeTask(taskNumber);
            ui.printTaskRemoved(task, tasks.retrieveAllTasks());
        } else {
            throw new DukeException("Invalid task number!");
        }
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
