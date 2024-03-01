package Nick.command;

import Nick.Storage;
import Nick.TaskList;
import Nick.Ui;
import Nick.task.Task;

public class DeleteCommand extends Command {
    String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

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
