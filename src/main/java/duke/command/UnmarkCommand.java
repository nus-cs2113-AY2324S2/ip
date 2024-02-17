package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command for marking tasks as incomplete.
 */
public class UnmarkCommand implements Command {

    private final int INDEX;

    /**
     * Constructs a new UnmarkCommand with index of the specified task provided by the user.
     *
     * @param index Index of the task to be marked as incomplete.
     * @throws DukeException If the user did not provide any index of the task to be marked.
     */
    public UnmarkCommand(String index) throws DukeException {
        if (index.isEmpty()) {
            throw new DukeException("Exceed Charge....\n\t " +
                    "OOPS!!! The index of a task to be marked as undone cannot be empty.\n\t " +
                    "unmark: marks a task in the task list as undone.\n\t " +
                    "Parameters: INDEX\n\t " +
                    "Example: unmark 1");
        } else {
            this.INDEX = Integer.parseInt(index.trim()) - 1;
        }
    }

    /**
     * Executes the command by marking the task as incomplete.
     * Displays the marked task and its new status on the screen.
     *
     * @param taskList The lists of tasks of the Duke chatbot.
     * @param ui The user interface of the Duke chatbot.
     * @param storage The file storage of the Duke chatbot.
     * @throws DukeException If there is an error in the user's input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (INDEX < 0 || INDEX >= taskList.size()) {
            throw new DukeException("Exceed Charge....\n\t " +
                    "OOPS!!! Unable to mark invalid task as undone.");
        } else {
            Task undoneTask = taskList.get(INDEX);
            undoneTask.markAsUndone();
            storage.editTaskList(taskList);
            ui.printMessage("OK, I've marked this task as not done yet:\n\t   " + undoneTask);
        }
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
