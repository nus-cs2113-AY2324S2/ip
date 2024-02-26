package jeff.commands;

import jeff.Command;
import jeff.Printer;
import jeff.Storage;
import jeff.TaskList;
import jeff.tasks.Todo;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand object with the given description.
     *
     * @param description Description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command by creating a new todo task, adding it to the task list,
     * appending it to storage, and printing a message indicating the task has been added.
     */
    @Override
    public void execute() {
        Todo todo = new Todo(description);
        TaskList.add(todo);
        Storage.appendTask(todo);
        Printer.printAddTask();
    }
}
