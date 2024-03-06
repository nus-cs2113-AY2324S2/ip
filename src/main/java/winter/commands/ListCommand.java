package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;
import winter.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        displayList(tasks.getTaskArrayList(),ui);
    }

    // Method for displaying list
    private void displayList(ArrayList<Task> tasks, UI ui) {
        for (Task task : tasks) {
            ui.showIndent();
            System.out.println((task.getOrder() + 1) + "." + task);
            /*switch (task.getType()) {
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
            }*/
        }
        //ui.showLine();
    }
}
