package carrot.command;

import carrot.parser.Parser;
import carrot.task.TaskList;
import carrot.task.Task;
import carrot.task.Deadline;

/**
 * Represents a command to add a new deadline task.
 * Extends the {@link Command} abstract class.
 */
public class DeadlineCommand extends Command {

    /**
     * Executes the deadline command, adding a new deadline task to the task list.
     *
     * @param userInput the user input string containing the deadline command, description of task and deadline date
     */
    @Override
    public void execute(String userInput) {
        String[] commandArguments =
                Parser.getCommandArguments(CommandType.DEADLINE, userInput);
        Task task = new Deadline(commandArguments[0], commandArguments[1]);

        TaskList.addTask(task);
    }
}
