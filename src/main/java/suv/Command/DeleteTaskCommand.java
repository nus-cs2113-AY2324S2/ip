package suv.Command;

import suv.Helpers.Ui;
import suv.Task.Task;

import static suv.Task.TaskList.tasksList;

/**
 * The DeleteTaskCommand class represents a command to delete a task from the task list.
 * It takes an input string containing the index of the task to be deleted.
 */
public class DeleteTaskCommand {

    /**
     * Constructs a new DeleteTaskCommand object and performs the deletion of the specified task.
     *
     * @param input The input string containing the index of the task to be deleted.
     * @throws SuvException If there is an error while executing the delete task command.
     */
    public DeleteTaskCommand(String input) throws SuvException {
        int n = Integer.parseInt(input.split(" ")[1]);
        Task currentTask = tasksList.get(n - 1);
        tasksList.remove(n - 1);
        Ui.printDeleteTodoMessage(currentTask, tasksList.size());
    }
}
