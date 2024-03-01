package Gene.command;

import Gene.GeneException;
import Gene.task.TaskList;
import Gene.task.Todo;

/**
 * Represents a command to add todo tasks to the task list.
 */
public class TodoCommand {

    /**
     * Execute the todo command, adding the todo task into the task list.
     *
     * @param command  User input of the todo command.
     * @param taskList User list of tasks.
     * @throws GeneException If there are any formatting issues.
     */
    public static void execute(String command, TaskList taskList) throws GeneException {
        String[] parts = command.split(" ");
        if (parts.length <= 1) {
            throw new GeneException("Description cannot be empty for todo task.");
        }
        String toDoParts = command.replaceFirst("\\S+", "");
        Todo newToDo = new Todo(toDoParts.trim());
        taskList.addTask(newToDo);
    }
}
