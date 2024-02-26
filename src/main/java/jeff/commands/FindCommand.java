package jeff.commands;

import jeff.Command;
import jeff.Printer;
import jeff.Task;
import jeff.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String textToFind;

    public FindCommand(String textToFind) {
        this.textToFind = textToFind;
    }

    @Override
    public void execute() {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < TaskList.size(); i++) {
            if (TaskList.get(i).getDescription().contains(textToFind)) {
                matchingTasks.add(TaskList.get(i));
            }
        }
        if (matchingTasks.isEmpty()) {
            Printer.printNoMatchingTasks();
            return;
        }
        Printer.printMatchingTasks(matchingTasks);
    }
}
