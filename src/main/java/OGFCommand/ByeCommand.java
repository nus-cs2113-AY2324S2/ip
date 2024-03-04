package OGFCommand;

import OGFCore.Storage;
import OGFCore.TaskList;
import OGFCore.Ui;

public class ByeCommand extends Command{
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printByeMessage();
        return false;
    }
}
