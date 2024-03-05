package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.TaskList;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
