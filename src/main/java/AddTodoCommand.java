
import java.util.ArrayList;

/**
 * Represents a command to add a todo task to a task list.
 * This command is used to create a new todo task with a given description.
 * <p>
 * The description is the only required parameter for creating a todo task.
 * </p>
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructs an AddTodoCommand with the given description.
     * The description is used to create a new todo task.
     *
     * @param description the description of the todo task to be added
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a new todo task to the task list.
     * After adding the task, it updates the user interface to display the added task.
     *
     * @param tasks    the task list to which the todo task will be added
     * @param ui       the user interface to display messages
     * @param storage the storage to save the task list
     */
    @Override
    public ArrayList<Task> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The task's description is empty! Please specify and be responsible for your tasks");
        }

        Todo newTodo = new Todo(description);
        tasks.appendTask(newTodo);
        ui.printTaskAdded(newTodo, tasks.retrieveAllTasks());
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


