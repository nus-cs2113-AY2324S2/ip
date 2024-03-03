package bob.command;

import bob.utils.TaskManager;

/**
 * Class representing a "list" command.
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand Object.
     * @param taskManager Current TaskManager instance.
     */
    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        return taskManager.listTasks();
    }
}
