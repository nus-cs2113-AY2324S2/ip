package nick.command;

import nick.storage.Storage;
import nick.task.TaskList;
import nick.ui.Ui;

public class FindCommand extends Command {
    private TaskList tasks;
    private String findDescription;
    private int counter = 1;

    public FindCommand(String argument) {
        findDescription = argument;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the matching tasks in your list:");
        tasks.tasks.stream()
                .filter((t) -> t.description.contains(findDescription))
                .forEach(result -> System.out.println((counter++) + ". " + result.toString()));
    }
    public boolean isExit() {
        return false;
    }
}
