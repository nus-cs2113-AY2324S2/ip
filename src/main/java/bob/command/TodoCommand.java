package bob.command;

import bob.utils.TaskManager;

/**
 * Class representing a "todo" command.
 */
public class TodoCommand extends Command {
    private final String taskName;

    /**
     * Creates a TodoCommand Object.
     * @param taskManager Current TaskManager instance.
     * @param taskName Name of Todo Task to be created.
     */
    public TodoCommand(TaskManager taskManager, String taskName) {
        super(taskManager);
        this.taskName = taskName;
    }

    @Override
    public String executeCommand() {
        return taskManager.addTodo(this.taskName);
    }
}
