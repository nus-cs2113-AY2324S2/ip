package natsu.command;

import natsu.exception.InvalidCommandException;
import natsu.task.Todo;
import natsu.util.Printer;
import static natsu.util.TaskManager.list;

public class AddTodoCommand {

    public AddTodoCommand(String userInput) throws InvalidCommandException {
        if (userInput.length() <= CommandConstants.TODO_COMMAND_LENGTH) {
            throw new InvalidCommandException("     I'm terribly sorry, but the description of a todo cannot be empty. Please try again!");
        }
        String todoDescription = userInput.substring(CommandConstants.TODO_COMMAND_LENGTH).trim();
        Todo todo = new Todo(todoDescription);
        list.add(todo);
        Printer.printTaskAdded(todo.toString(), list.size());
    }
}
