package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;
import winter.task.Task;

import java.util.ArrayList;

/**
 * Represents the command given by the user to display the task list
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        displayList(tasks.getTaskArrayList(),ui);
    }

    /**
     * Displays the tasks in the task list in the correct format and with the correct numbering in the list
     * @param tasks The ArrayList containing the tasks
     * @param ui The user interface
     */
    private void displayList(ArrayList<Task> tasks, UI ui) {
        for (Task task : tasks) {
            ui.showIndent();
            switch (task.getType()) {
            case "D":
                System.out.println((task.getOrder() + 1)  + ". [D] " + task.getDoneCheckbox() + " "
                        + task.getTaskName() + " (by: " + task.getEndTime() + ")");
                break;
            case "E":
                System.out.println((task.getOrder() + 1) + ". [E] " + task.getDoneCheckbox() + " "
                        + task.getTaskName() + " (from: " + task.getStartTime()
                        + " to: " + task.getEndTime() + ")");
                break;
            default:
                System.out.println((task.getOrder() + 1) + ". [T]" + task.getDoneCheckbox() + " "
                        + task.getTaskName());
            }
        }
        //ui.showLine();
    }
}
