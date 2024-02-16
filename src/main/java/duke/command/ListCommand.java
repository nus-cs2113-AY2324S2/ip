package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command for listing all tasks
 */
public class ListCommand implements Command {

    /**
     * Executes the command by iterating through the task list and displaying every task.
     *
     * @param taskList The lists of tasks of the Duke chatbot.
     * @param ui The user interface of the Duke chatbot.
     * @param storage The file storage of the Duke chatbot.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        message.append((taskList.isEmpty()) ? "" : System.lineSeparator());
        for (int i = 0; i < taskList.size(); i++) {
            message.append("\t ").append(i + 1).append(".").append(taskList.get(i).toString())
                    .append((i == taskList.size() - 1) ? "" : System.lineSeparator());
        }
        ui.printMessage(message.toString());
    }

    /**
     * Indicates whether this is an exit command.
     * Returns false since this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
