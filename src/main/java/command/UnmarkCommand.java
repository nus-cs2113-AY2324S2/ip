package carrot.command;

import carrot.command.Command;
import carrot.parser.Parser;
import carrot.task.TaskList;
import carrot.ui.Ui;

public class UnmarkCommand extends Command {
    @Override
    public void execute(String userInput) {
        String taskIndex =
                Parser.getCommandArguments(CommandType.UNMARK, userInput)[0];
        int taskIndexInt =
                Integer.parseInt(taskIndex);

        try {
            TaskList.changeTaskStatus(false, taskIndexInt);
        } catch (Exception e) {
            Ui.printInvalidTaskIndexError();
        }
    }
}
