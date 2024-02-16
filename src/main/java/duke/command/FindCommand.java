package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand implements Command {

    private final String KEYWORD;

    public FindCommand(String keyword) throws DukeException {
        if (keyword.isEmpty()) {
            throw new DukeException("Exceed Charge....\n\t " +
                    "OOPS!!! The keyword of a task to be found cannot be empty.\n\t " +
                    "find: find tasks by searching for a keyword.\n\t " +
                    "Parameters: KEYWORD\n\t " +
                    "Example: find book");
        } else {
            this.KEYWORD = keyword;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
        int count = 1;
        for (Task currTask : taskList) {
            if (currTask.toString().contains(this.KEYWORD)) {
                message.append("\n\t ").append(count).append(".").append(currTask);
                count++;
            }
        }
        ui.printMessage(String.valueOf(message));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
