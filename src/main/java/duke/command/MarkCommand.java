package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand implements Command {

    private final int INDEX;

    public MarkCommand(String index) {
        this.INDEX = Integer.parseInt(index) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (INDEX < 0 || INDEX >= taskList.size()) {
            throw new DukeException("Exceed Charge.... \n\t " +
                    "OOPS!!! Unable to mark invalid task as done.");
        } else {
            Task doneTask = taskList.get(INDEX);
            doneTask.markAsDone();
            ui.printMessage("Nice! I've marked this task as done:\n\t   " + doneTask);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
