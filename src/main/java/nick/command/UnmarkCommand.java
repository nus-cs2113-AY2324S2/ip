package nick.command;

import nick.storage.Storage;
import nick.task.TaskList;
import nick.ui.Ui;

/**
 * Represents the Unmark command which inherits from the Command class.
 * It includes the execute method which unmark a task as uncompleted from the ArrayList tasks.
 */
public class UnmarkCommand extends Command {
    String arguments;

    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Marks a task from the ArrayList tasks as uncompleted via markAsUndone() method.
     *
     * @param tasks ArrayList of Task objects.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int number = Integer.parseInt(arguments);
        tasks.tasks.get(number - 1).markAsUndone();
        ui.showLine();
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println("\t" +
                "[" +
                tasks.tasks.get(number - 1).getStatusIcon() +
                "] " +
                tasks.tasks.get(number - 1).description);
        ui.showLine();
        Storage.saveData(tasks.tasks);
    }
}
