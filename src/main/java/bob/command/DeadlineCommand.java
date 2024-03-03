package bob.command;

import bob.utils.TaskManager;

import java.time.LocalDateTime;

/**
 * Class representing a "deadline" command.
 */
public class DeadlineCommand extends Command {
    private final String taskName;
    private final LocalDateTime dueDate;

    /**
     * Creates a DeadlineCommand Object.
     * @param taskManager Current TaskManager instance.
     * @param taskName Name of Deadline Task to be created.
     * @param dueDate Due Date of Deadline Task.
     */
    public DeadlineCommand(TaskManager taskManager, String taskName, LocalDateTime dueDate) {
        super(taskManager);
        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    @Override
    public String executeCommand() {
        return taskManager.addDeadline(this.taskName, this.dueDate);
    }
}
