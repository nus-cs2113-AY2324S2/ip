package natsu.command;

import natsu.exception.InvalidCommandException;
import natsu.task.Todo;
import natsu.util.Ui;

import static natsu.util.TaskList.list;

/**
 * Represents a command to add a new todo task to the task list.
 * This command parses user input to extract the todo description and
 * then creates a new {@link Todo} object to add to the task list.
 */
public class AddTodoCommand {

    /**
     * Constructs an {@code AddTodoCommand} and adds a new todo task to the task list based on user input.
     * Validates that the user input contains a description for the todo task.
     *
     * @param userInput The full user input string containing the todo description.
     * @throws InvalidCommandException If the description of the todo is empty.
     */
    public AddTodoCommand(String userInput) throws InvalidCommandException {
        if (userInput.length() <= CommandConstants.TODO_COMMAND_LENGTH) {
            throw new InvalidCommandException("     I'm terribly sorry, but the description of a todo cannot be empty. Please try again!");
        }
        String todoDescription = userInput.substring(CommandConstants.TODO_COMMAND_LENGTH).trim();
        Todo todo = new Todo(todoDescription);
        list.add(todo);
        Ui.printTaskAdded(todo.toString());
    }
}
