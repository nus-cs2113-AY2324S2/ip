package bob.command;

import bob.utils.TaskManager;

/**
 * Class representing a "find" command.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand Object.
     * @param taskManager Current TaskManager instance.
     * @param keyword Search keyword to be used.
     */
    public FindCommand(TaskManager taskManager, String keyword) {
        super(taskManager);
        this.keyword = keyword;
    }

    @Override
    public String executeCommand() {
        return taskManager.findTask(keyword);
    }
}
