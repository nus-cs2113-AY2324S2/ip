package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;

public class ListTaskCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showTask("The task list is currently empty.");
        } else {
            ui.listTasks(tasks);
        }
    }
}
