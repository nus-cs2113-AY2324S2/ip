package carrot.command;

import carrot.parser.Parser;
import carrot.task.TaskList;
import carrot.task.Task;
import carrot.task.Event;

public class EventCommand extends Command {
    @Override
    public void execute(String userInput) {
        String[] commandArguments =
                Parser.getCommandArguments(CommandType.EVENT, userInput);
        Task task = new Event(commandArguments[0], commandArguments[1], commandArguments[2]);

        TaskList.addTask(task);
    }
}
