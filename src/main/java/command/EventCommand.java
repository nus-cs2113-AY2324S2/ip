package carrot.command;

import carrot.parser.Parser;
import carrot.task.TaskList;
import carrot.task.Task;
import carrot.task.Event;

/**
 * Represents a command to add a new event task.
 * Extends the {@link Command} abstract class.
 */
public class EventCommand extends Command {

    /**
     * Executes the event command, adding a new event task to the task list.
     *
     * @param userInput the user input string containing the event command, task description, from date and to date.
     */
    @Override
    public void execute(String userInput) {
        String[] commandArguments =
                Parser.getCommandArguments(CommandType.EVENT, userInput);
        Task task = new Event(commandArguments[0], commandArguments[1], commandArguments[2]);

        TaskList.addTask(task);
    }
}
