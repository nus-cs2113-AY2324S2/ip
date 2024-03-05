package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Todo;

/**
 * A command to add a new todo task to the task list.
 * Encapsulates the information need to create a new Todo task,
 * including the description of the task.
 */
public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by creating a new Todo task,
     * adding it to the task list and displaying a confirmation message to the user.
     *
     * @param tasks The TaskList currently managed by KikuBot, which can be modified by the command.
     * @param ui The Ui component of KikuBot used for interacting with the user.
     * @param storage The Storage component of KikuBot, responsible for tracking task list changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        System.out.println("Got it. I've added this task: \n" + todo);
        ui.showTask("Now you have " + tasks.getTasks().size() + " tasks in the list :>");
    }
}
