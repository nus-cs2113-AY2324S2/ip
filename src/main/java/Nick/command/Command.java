package Nick.command;

import Nick.TaskList;
import Nick.Ui;
import Nick.Storage;

public abstract class Command {
    private TaskList tasks;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return false;
    }
}
