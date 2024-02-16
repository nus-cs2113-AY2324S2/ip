package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand implements Command {

    private final int INDEX;

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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (INDEX < 0 || INDEX >= taskList.size()) {
            throw new DukeException("Exceed Charge.... \n\t " +
                    "OOPS!!! Unable to mark invalid task as undone.");
        } else {
            Task undoneTask = taskList.get(INDEX);
            undoneTask.markAsUndone();
            storage.editTaskList(taskList);
            ui.printMessage("OK, I've marked this task as not done yet:\n\t   " + undoneTask);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
