package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;

public class MarkTaskCommand extends Command {
    private final int taskIndex;

    public MarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex >= 0 && taskIndex < tasks.getTasks().size()) {
            tasks.markTaskAsDone(taskIndex);
            ui.showTask("Awesomeee! I've marked this task as done: \n"
                    + tasks.getTasks().get(taskIndex));
        } else {
            ui.errorMessage("OPPS! Task index out of bounds :<");
        }
    }
}
