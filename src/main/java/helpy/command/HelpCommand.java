package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.TaskList;

public class HelpCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
