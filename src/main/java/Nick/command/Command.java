package Nick.command;

import Nick.task.TaskList;
import Nick.ui.Ui;
import Nick.storage.Storage;

public abstract class Command {
    private TaskList tasks;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return false;
    }
}
