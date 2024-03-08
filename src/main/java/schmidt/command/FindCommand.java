package schmidt.command;

import schmidt.storage.Storage;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

/**
 * Represents a command to find a keyword in the task list.
 */
public class FindCommand extends Command {
    public static final String COMMAND = "find";
    private final String keyword;

    /**
     * Constructs a find command with the keyword to find.
     *
     * @param keyword the keyword to find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks that match the keyword and prints them to the user interface.
     *
     * @param tasks the list of tasks.
     * @param ui the user interface.
     * @param storage the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.printMatchingTasks(matchingTasks);
    }
}
