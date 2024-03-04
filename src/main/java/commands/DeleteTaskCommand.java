package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Task;

public class DeleteTaskCommand extends Command {
    private final int taskIndex;
    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex >= 0 && taskIndex < tasks.getTasks().size()) {
            Task removedTask = tasks.getTasks().get(taskIndex);
            tasks.deleteTask(taskIndex);
            ui.showTask("Alright, I've removed this task: \n" + removedTask);
            ui.showTask("Now you have " + tasks.getTasks().size() + " tasks in the list~");
        } else {
            ui.errorMessage("Task index out of bounds.");
        }
    }
}
