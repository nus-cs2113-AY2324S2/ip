package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command for marking tasks as complete.
 */
public class MarkCommand implements Command {

    private final int INDEX;

    /**
     * Constructs a new MarkCommand with index of the specified task provided by the user.
     *
     * @param index Index of the task to be marked as complete.
     * @throws DukeException If the user did not provide any index of the task to be marked.
     */
    public MarkCommand(String index) throws DukeException {
        if (index.isEmpty()) {
            throw new DukeException("Exceed Charge....\n\t " +
                    "OOPS!!! The index of a task to be marked as done cannot be empty.\n\t " +
                    "mark: marks a task in the task list as done.\n\t " +
                    "Parameters: INDEX\n\t " +
                    "Example: mark 1");
        } else {
            this.INDEX = Integer.parseInt(index.trim()) - 1;
        }
    }

    /**
     * Executes the command by marking the task as complete.
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
                    "OOPS!!! Unable to mark invalid task as done.");
        } else {
            Task doneTask = taskList.get(INDEX);
            doneTask.markAsDone();
            storage.editTaskList(taskList);
            ui.printMessage("Nice! I've marked this task as done:\n\t   " + doneTask);
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
