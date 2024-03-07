package command;

import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.Task;
import tasktype.TaskList;
import ui.Ui;

public class FindCommand implements Command {
    protected String key;

    public FindCommand(String input) throws JingHaoExceptions {
        if(input.isEmpty()) {
            String invalidKey = "Sorry, the keyword to find cannot be empty\n" +
                    "Please use the valid command: find (keyword)\n" +
                    "For example: find book";
            throw new JingHaoExceptions(invalidKey);
        }
        this.key = input.toLowerCase();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions {
        TaskList taskMatch = new TaskList();
        for(Task currentTask: taskList) {
            if(currentTask.toString().toLowerCase().contains(key)) {
                taskMatch.add(currentTask);
            }
        }
        if(taskMatch.isEmpty()) {
            String emptyMessage = "There are no matching task found.";
            ui.printMessage(emptyMessage);
        } else {
            ui.printWithoutDivider("Here are the matching tasks in your list:");
            int i = 1;
            for (Task item: taskMatch) {
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
