package suv.Command;

import suv.Helpers.Ui;
import suv.Task.Task;

import static suv.Task.TaskList.tasksList;

public class MarkCommand {
    public MarkCommand(String input) throws SuvException{
        int n = Integer.parseInt(input.split(" ")[1]);
        Task currentTask = tasksList.get(n - 1);
        currentTask.mark();
        Ui.printMarkMessage(currentTask.getDescription());
    }
}
