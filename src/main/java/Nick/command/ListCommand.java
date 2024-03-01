package Nick.command;

import Nick.Storage;
import Nick.TaskList;
import Nick.Ui;

public class ListCommand extends Command {
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
