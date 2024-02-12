package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    boolean isExit();
}
