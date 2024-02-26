package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command for finding tasks with a given keyword.
 */
public class FindCommand implements Command {

    private final String KEYWORD;

    /**
     * Constructs a new FindCommand with keyword of the specified tasks provided by the user.
     *
     * @param keyword Keyword of the tasks to search for.
     * @throws DukeException If the user did not provide any keyword of the tasks to be searched.
     */
    public FindCommand(String keyword) throws DukeException {
        if (keyword.isEmpty()) {
            String exceptionMsg = "Exceed Charge....\n\t " +
                    "OOPS!!! The keyword of a task to be found cannot be empty.\n\t " +
                    "find: find tasks by searching for a keyword.\n\t " +
                    "Parameters: KEYWORD\n\t " +
                    "Example: find book";
            throw new DukeException(exceptionMsg);
        }
        this.KEYWORD = keyword;
    }

    /**
     * Executes the command by searching for the keyword in the taskList.
     * Displays the found tasks on the screen.
     *
     * @param taskList The lists of tasks of the Duke chatbot.
     * @param ui The user interface of the Duke chatbot.
     * @param storage The file storage of the Duke chatbot.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
        int count = 1;
        for (Task currTask : taskList) {
            if (currTask.toString().contains(this.KEYWORD)) {
                message.append("\n\t ").append(count).append(".").append(currTask);
                count++;
            }
        }
        ui.printMessage(String.valueOf(message));
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
