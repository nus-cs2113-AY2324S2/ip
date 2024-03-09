/**
 * Interface for executing commands.
 */
public interface Command {

    /**
     * Execute the command.
     *
     * @param tasksList The task list to operate on.
     */
    void execute(TaskList tasksList);
}

