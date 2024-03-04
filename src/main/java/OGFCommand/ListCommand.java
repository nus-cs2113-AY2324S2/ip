package OGFCommand;

import OGFCore.Storage;
import OGFCore.TaskList;
import OGFCore.Ui;

public class ListCommand extends Command {
    public boolean execute(TaskList taskList, Ui ui, Storage storage){
        ui.printList(taskList);
        return true;
    }
}
