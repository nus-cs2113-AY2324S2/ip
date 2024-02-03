package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand implements Command {

    private final int INDEX;

    public UnmarkCommand(String index) {
        this.INDEX = Integer.parseInt(index) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (INDEX < 0 || INDEX >= taskList.size()) {
            throw new DukeException("Exceed Charge.... \n\t " +
                    "OOPS!!! Unable to mark invalid task as undone.");
        } else {
            Task undoneTask = taskList.get(INDEX);
            undoneTask.markAsUndone();
            ui.printMessage("Nice! I've marked this task as not done yet:\n\t   " + undoneTask);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
