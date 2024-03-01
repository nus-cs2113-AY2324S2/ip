package Nick.command;

import Nick.storage.Storage;
import Nick.task.TaskList;
import Nick.ui.Ui;

public class MarkCommand extends Command {
    String arguments;

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

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
    }

    public boolean isExit() {
        return false;
    }
}
