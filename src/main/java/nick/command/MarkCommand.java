package nick.command;

import nick.storage.Storage;
import nick.task.TaskList;
import nick.ui.Ui;

/**
 * Represents the Mark command which inherits from the Command class.
 * It includes the execute method which marks a task as completed from the ArrayList tasks.
 */
public class MarkCommand extends Command {
    String arguments;

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Marks a task from the ArrayList tasks as completed via markAsDone() method.
     *
     * @param tasks ArrayList of Task objects.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int number = Integer.parseInt(arguments);
        tasks.tasks.get(number - 1).markAsDone();
        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" +
                "[" +
                tasks.tasks.get(number - 1).getStatusIcon() +
                "] " +
                tasks.tasks.get(number - 1).description);
        ui.showLine();
        Storage.saveData(tasks.tasks);
    }
}
