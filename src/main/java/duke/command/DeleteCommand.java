package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand implements Command {

    private final int INDEX;

    public DeleteCommand(String index) throws DukeException {
        if (index.isEmpty()) {
            throw new DukeException("Exceed Charge....\n\t " +
                    "OOPS!!! The index of a task to be deleted cannot be empty.\n\t " +
                    "delete: deletes a task from the task list.\n\t " +
                    "Parameters: INDEX\n\t " +
                    "Example: delete 1");
        } else {
            this.INDEX = Integer.parseInt(index) - 1;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (INDEX < 0 || INDEX >= taskList.size()) {
            throw new DukeException("Exceed Charge.... \n\t " +
                    "OOPS!!! Unable to delete invalid task.");
        } else {
            Task deletedTask = taskList.get(INDEX);
            taskList.remove(INDEX);
            storage.editTaskList(taskList);
            String msg = (taskList.size() == 1) ? "task" : "tasks";
            ui.printMessage("Noted. I've removed this task:\n\t   " + deletedTask
                    + "\n\t Now you have " + taskList.size() + " " + msg + " in the list.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
