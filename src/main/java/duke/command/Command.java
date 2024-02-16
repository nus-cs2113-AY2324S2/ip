package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a general command of the Duke chatbot.
 */
public interface Command {
    /**
     * Executes the respective command.
     *
     * @param taskList The lists of tasks of the Duke chatbot.
     * @param ui The user interface of the Duke chatbot.
     * @param storage The file storage of the Duke chatbot.
     * @throws DukeException If there is an error in the user's input.
     * @throws IOException If there is an error appending the new task.
     */
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Indicates whether the respective command is an exit command.
     *
     * @return true if it is an exit command, otherwise false.
     */
    boolean isExit();
}
