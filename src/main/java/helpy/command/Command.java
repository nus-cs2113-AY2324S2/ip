package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Task;
import helpy.task.TaskList;

import java.util.ArrayList;

public class Command {
    protected String commandBody = "";
    protected boolean isExit = false;

    public Command() {}

    public Command(String body) {
        commandBody = body;
    }

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {}
}
