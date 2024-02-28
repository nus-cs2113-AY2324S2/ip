package suv.Command;

import suv.Helpers.Ui;

import static suv.Task.TaskList.tasksList;

public class UnMarkCommand {
    public UnMarkCommand(String input) throws SuvException{
        int n = Integer.parseInt(input.split(" ")[1]);
        tasksList.get(n - 1).unMark();
        Ui.printUnmarkMessage(tasksList.get(n - 1).getDescription());
    }
}
