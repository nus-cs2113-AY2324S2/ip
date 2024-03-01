package Nick.command;

import Nick.TaskList;
import Nick.Ui;
import Nick.Storage;

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
