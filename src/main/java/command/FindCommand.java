package carrot.command;

import carrot.parser.Parser;
import carrot.task.Task;
import carrot.task.TaskList;
import carrot.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    @Override
    public void execute(String userInput) {
        String[] commandArguments =
                Parser.getCommandArguments(CommandType.FIND, userInput);

        String wordToCheck = commandArguments[0];
        ArrayList<Task> listOfTasks = TaskList.getTaskList();

        Ui.printFindTaskOutput(listOfTasks, wordToCheck);
    }
}
