import java.util.ArrayList;

/**
 * Represents a command to add a deadline task to a task list.
 * This command parses the command string to extract the task description and the deadline time.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs an AddDeadlineCommand with the given command string.
     * The command string should contain the task description followed by "/by" and the deadline time.
     *
     * @param command the command string containing the task description and deadline time
     */
    public AddDeadlineCommand(String command) throws DukeException {
        String[] parts = command.split("/by");
        if (parts.length < 2) {
            throw new DukeException("Please use the format: deadline [description] /by [add time]");
        }
        this.description = parts[0].trim();
        this.by = parts.length > 1 ? parts[1].trim() : "";
    }

    /**
     * Executes the command to add a new deadline task to the task list.
     * If the deadline time is not specified, a DukeException is thrown.
     *
     * @param tasks    the task list to which the deadline task will be added
     * @param ui       the user interface to display messages
     * @param storage the storage to save the task list
     * @throws DukeException if the deadline time is not specified
     */
    @Override
    public ArrayList<Task> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (by.isEmpty()) {
            throw new DukeException("Please use '/by' to specify the deadline time.");
        }
        Deadline newDeadline = new Deadline(description, by);
        tasks.appendTask(newDeadline);
        ui.printTaskAdded(newDeadline, tasks.retrieveAllTasks());
        return null;
    }

    /**
     * Indicates whether this command is intended to exit the application.
     *
     * @return false, as this command does not exit the application
     */
    @Override
    public boolean isExit() {
        return false;
    }

}


