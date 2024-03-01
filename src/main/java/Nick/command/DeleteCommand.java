package Nick.command;

import Nick.storage.Storage;
import Nick.task.TaskList;
import Nick.ui.Ui;
import Nick.task.Task;

/**
 * Represents the Delete command which inherits from the Command class.
 * It includes the execute method which removes a task from the ArrayList tasks.
 */
public class DeleteCommand extends Command {
    String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command by deleting the task from ArrayList task based on integer from argument.
     *
     * @param tasks ArrayList of Task objects.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int number = Integer.parseInt(arguments);
        Task taskToDelete = tasks.tasks.get(number - 1);
        tasks.tasks.remove(taskToDelete);

        ui.showLine();
        System.out.println("Sure thing! I've removed this task:");
        System.out.println("\t" + taskToDelete);
        System.out.println("Now you have " + tasks.tasks.size() + " tasks in the list.");
        ui.showLine();
    }
}
