package carrot.command;

import carrot.command.Command;
import carrot.parser.Parser;
import carrot.task.TaskList;
import carrot.ui.Ui;

public class MarkCommand extends Command {
    @Override
    public void execute(String userInput) {
        String taskIndex =
                Parser.getCommandArguments(CommandType.MARK, userInput)[0];
        int taskIndexInt =
                Integer.parseInt(taskIndex);

        try {
            TaskList.changeTaskStatus(true, taskIndexInt);
        } catch (Exception e) {
            Ui.printInvalidTaskIndexError();
        }
    }
}
