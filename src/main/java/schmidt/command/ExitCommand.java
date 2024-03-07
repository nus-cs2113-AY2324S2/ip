package schmidt.command;

import schmidt.storage.Storage;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
