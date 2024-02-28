package carrot.command;

import carrot.parser.Parser;
import carrot.task.TaskList;
import carrot.task.Task;
import carrot.task.Todo;

public class TodoCommand extends Command {
    @Override
    public void execute(String userInput) {
        String[] commandArguments =
                Parser.getCommandArguments(CommandType.TODO, userInput);
        Task task = new Todo(commandArguments[0]);

        TaskList.addTask(task);
    }
}
