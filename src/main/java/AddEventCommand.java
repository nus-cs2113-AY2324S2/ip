import java.util.ArrayList;

/**
 * Represents a command to add an event task to a task list.
 * This command parses the command string to extract the event description, start time, and end time.
 * <p>
 * The command string should follow the format: "event [description] /from [start time] /to [end time]".
 * For example: "event Meeting /from 2024-04-01 10:00 /to 2024-04-01 12:00".
 * </p>
 */

public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an AddEventCommand with the given command string.
     * The command string must include the event description, start time, and end time.
     *
     * @param command the command string containing the event description, start time, and end time
     * @throws DukeException if the command string is malformed or missing required parts
     */
    public AddEventCommand(String command) throws DukeException {
        String[] parts = command.split("/from | /to ");
        if (parts.length < 3) {
            throw new DukeException("Please use the format: event [description] /from [start time] /to [end time]");
        }
        this.description = parts[0].trim();
        this.from = parts[1].trim();
        this.to = parts[2].trim();
    }

    /**
     * Executes the command to add a new event task to the task list.
     *
     * @param tasks    the task list to which the event task will be added
     * @param ui       the user interface to display messages
     * @param storage the storage to save the task list
     * @throws DukeException if the task list cannot be updated
     */
    @Override
    public ArrayList<Task> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event newEvent = new Event(description, from, to);
        tasks.appendTask(newEvent);
        ui.printTaskAdded(newEvent, tasks.retrieveAllTasks());
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

