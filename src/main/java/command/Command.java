package command;

import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Represents a general command of the Duke chatbot.
 */
public interface Command {

    /**
     * Executes the respective command.
     *
     * @param taskList The list of task in the JingHao chatbot.
     * @param ui The user interface of the JingHao chatbot.
     * @param storage The file storage of the JingHao chatbot.
     * @throws JingHaoExceptions If there is an error in the user's input
     * @throws IOException If there is an error appending the new task.
     */
    void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions, IOException;

    /**
     * Determines whether the command is an exit command.
     *
     * @return true if it is an exit command, otherwise false.
     */
    boolean isExit();
}
