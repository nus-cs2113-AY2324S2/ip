package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Task;
import helpy.task.TaskList;

import java.util.ArrayList;

public class ExitCommand extends Command{
    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayGoodbye();
    }
}
