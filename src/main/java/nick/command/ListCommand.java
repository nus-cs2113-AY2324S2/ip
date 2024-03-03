package nick.command;

import nick.storage.Storage;
import nick.task.TaskList;
import nick.ui.Ui;

/**
 * Represents the List command which inherits from the Command class.
 * It includes the execute method which lists all task objects in the ArrayList tasks.
 */
public class ListCommand extends Command {

    /**
     * Iterates through each task object in the ArrayList and prints out each task object.
     *
     * @param tasks ArrayList of Task objects.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskCount = tasks.tasks.size();
        ui.showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t" + Integer.toString(i + 1) + "." + tasks.tasks.get(i));
        }
        ui.showLine();
    }
}
