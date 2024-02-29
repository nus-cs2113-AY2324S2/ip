package suv.Command;

import suv.Helpers.Ui;
import suv.Task.Task;

import static suv.Task.TaskList.tasksList;

/**
 * The MarkCommand class represents a command to mark a task as done.
 * It takes an input string containing the index of the task to be marked.
 */
public class MarkCommand {

    /**
     * Constructs a new MarkCommand object and marks the specified task as done.
     *
     * @param input The input string containing the index of the task to be marked.
     * @throws SuvException If there is an error while executing the mark command.
     */
    public MarkCommand(String input) throws SuvException{
        int n = Integer.parseInt(input.split(" ")[1]);
        Task currentTask = tasksList.get(n - 1);
        currentTask.mark();
        Ui.printMarkMessage(currentTask.getDescription());
    }
}
