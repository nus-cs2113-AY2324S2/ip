package command;

import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.TaskList;
import ui.Ui;

import java.io.IOException;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions, IOException;
    boolean isExit();
}
