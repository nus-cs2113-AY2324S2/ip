package Nick.command;

import Nick.Storage;
import Nick.TaskList;
import Nick.Ui;

public class UnmarkCommand extends Command {
    String arguments;

    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

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
    }

    public boolean isExit() {
        return false;
    }
}
