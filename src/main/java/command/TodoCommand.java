package carrot.command;

import carrot.parser.Parser;
import carrot.task.TaskList;
import carrot.task.Task;
import carrot.task.Todo;

/**
 * Represents a command to add a new todo task.
 * Extends the {@link Command} abstract class.
 */
public class TodoCommand extends Command {

    /**
     * Executes the todo command, adding a new todo task to the task list.
     *
     * @param userInput the user input string containing the todo command and the task description
     */
    @Override
    public void execute(String userInput) {
        String[] commandArguments =
                Parser.getCommandArguments(CommandType.TODO, userInput);
        Task task = new Todo(commandArguments[0]);

        TaskList.addTask(task);
    }
}
