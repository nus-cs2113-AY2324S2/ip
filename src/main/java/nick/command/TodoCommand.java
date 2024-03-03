package nick.command;

import nick.storage.Storage;
import nick.task.TaskList;
import nick.ui.Ui;
import nick.task.Task;
import nick.task.Todo;

/**
 * Represents the Todo command which inherits from the Command class.
 * It includes the execute method which adds a Todo task to the ArrayList tasks.
 */
public class TodoCommand extends Command {
    String arguments;

    public TodoCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes by creating a Todo task from the task description.
     * Adds the created Todo task into ArrayList tasks and prints the add tasks message.
     *
     * @param tasks ArrayList of Task objects.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(arguments);
        tasks.tasks.add(task);
        ui.printAddTaskMsg(task.toString(), tasks.tasks.size());
        Storage.saveData(tasks.tasks);
    }
}
