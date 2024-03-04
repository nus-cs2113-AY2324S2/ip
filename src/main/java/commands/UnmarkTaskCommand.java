package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;

public class UnmarkTaskCommand extends Command {
    private final int taskIndex;

    public UnmarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex >= 0 && taskIndex < tasks.getTasks().size()) {
            tasks.markTaskAsNotDone(taskIndex);
            ui.showTask("OK, I've marked this task as not done yet: \n"
                    + tasks.getTasks().get(taskIndex));
        } else {
            ui.errorMessage("Task index out of bounds.");
        }
    }
}
