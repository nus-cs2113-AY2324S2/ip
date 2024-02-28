package carrot.command;

import carrot.parser.Parser;
import carrot.task.TaskList;
import carrot.task.Task;
import carrot.task.Deadline;

public class DeadlineCommand extends Command {
    @Override
    public void execute(String userInput) {
        String[] commandArguments =
                Parser.getCommandArguments(CommandType.DEADLINE, userInput);
        Task task = new Deadline(commandArguments[0], commandArguments[1]);

        TaskList.addTask(task);
    }
}
