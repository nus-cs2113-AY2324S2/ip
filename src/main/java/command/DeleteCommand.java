package carrot.command;

import carrot.parser.Parser;
import carrot.task.TaskList;
import carrot.ui.Ui;

public class DeleteCommand extends Command {
    @Override
    public void execute(String userInput) {
        String taskIndex =
                Parser.getCommandArguments(carrot.command.CommandType.DELETE, userInput)[0];
        int taskIndexInt =
                Integer.parseInt(taskIndex) - 1;

        try {
            TaskList.deleteTask(taskIndexInt);
        } catch (Exception e) {
            Ui.printInvalidTaskIndexError();
        }
    }
}
