package suv.Command;

import suv.Helpers.Ui;

import static suv.Task.TaskList.tasksList;

/**
 * The UnMarkCommand class represents a command to mark a task as not done.
 * It takes an input string containing the index of the task to be unmarked.
 */
public class UnMarkCommand {

    /**
     * Constructs a new UnMarkCommand object and performs the unmarking operation on the specified task.
     *
     * @param input The input string containing the index of the task to be unmarked.
     * @throws SuvException If there is an error while executing the unmark command.
     */
    public UnMarkCommand(String input) throws SuvException{
        int n = Integer.parseInt(input.split(" ")[1]);
        tasksList.get(n - 1).unMark();
        Ui.printUnmarkMessage(tasksList.get(n - 1));
    }
}
