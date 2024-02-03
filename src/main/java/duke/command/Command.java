package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    boolean isExit();
}
