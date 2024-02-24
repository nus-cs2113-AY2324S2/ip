package command;

import storage.Storage;
import tasktype.Task;
import tasktype.TaskList;
import ui.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            ui.printMessage("You have no task.");
        } else {
            ui.printWithoutDivider("Here are the tasks in your list:");
            int i = 1;
            for (Task item: taskList) {
                System.out.println( i + "." + item );
                i++;
            }
            ui.printDivider();
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
