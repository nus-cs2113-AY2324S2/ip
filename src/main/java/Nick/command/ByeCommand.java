package Nick.command;

import Nick.task.TaskList;
import Nick.ui.Ui;
import Nick.storage.Storage;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
