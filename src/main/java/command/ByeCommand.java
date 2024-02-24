package command;

import storage.Storage;
import tasktype.TaskList;
import ui.Ui;

public class ByeCommand implements Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.farewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
