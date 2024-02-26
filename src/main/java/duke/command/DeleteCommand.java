package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command for deleting tasks.
 */
public class DeleteCommand implements Command {

    private final int INDEX;

    /**
     * Constructs a new DeleteCommand with index of the specified task provided by the user.
     *
     * @param index Index of the task to be deleted.
     * @throws DukeException If the user did not provide any index of the task to be deleted.
     */
    public DeleteCommand(String index) throws DukeException {
        if (index.isEmpty()) {
            String indexEmptyMsg = "Exceed Charge....\n\t " +
                    "OOPS!!! The index of a task to be deleted cannot be empty.\n\t " +
                    "delete: deletes a task from the task list.\n\t " +
                    "Parameters: INDEX\n\t " +
                    "Example: delete 1";
            throw new DukeException(indexEmptyMsg);
        }
        this.INDEX = Integer.parseInt(index.trim()) - 1;
    }

    /**
     * Executes the command by deleting the specified task.
     * Displays the deleted task on the screen.
     *
     * @param taskList The lists of tasks of the Duke chatbot.
     * @param ui The user interface of the Duke chatbot.
     * @param storage The file storage of the Duke chatbot.
     * @throws DukeException If there is an error in the user's input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (INDEX < 0 || INDEX >= taskList.size()) {
            String indexOutOfBoundsMsg = "Exceed Charge....\n\t OOPS!!! Unable to delete invalid task.";
            throw new DukeException(indexOutOfBoundsMsg);
        }
        Task deletedTask = taskList.get(INDEX);
        taskList.remove(INDEX);
        storage.editTaskList(taskList);
        String msg = (taskList.size() == 1) ? "task" : "tasks";
        ui.printMessage("Noted. I've removed this task:\n\t   " + deletedTask
                + "\n\t Now you have " + taskList.size() + " " + msg + " in the list.");
    }

    /**
     * Indicates whether this is an exit command.
     * Returns false since this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
