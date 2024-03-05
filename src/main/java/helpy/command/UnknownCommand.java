package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.TaskList;

public class UnknownCommand extends Command{
    private String fullCommand = "";

    public UnknownCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showUnknownCommandErr(fullCommand);
    }
}
