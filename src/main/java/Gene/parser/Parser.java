package Gene.parser;

import Gene.GeneException;
import Gene.command.DeadlineCommand;
import Gene.command.EventCommand;
import Gene.command.MarkCommand;
import Gene.command.TodoCommand;
import Gene.task.TaskList;
import Gene.ui.Ui;

public class Parser {
    public static void processCommand(TaskList taskList, String command) throws GeneException {
        String[] parts = command.split(" ");
        String action = parts[0].toLowerCase();

        switch (action) {
            case "list":
                taskList.printListItems();
                break;

            case "mark":
                MarkCommand.execute(command, taskList, true);
                break;

            case "unmark":
                MarkCommand.execute(command, taskList, false);
                break;

            case "todo":
                TodoCommand.execute(command, taskList);
                break;

            case "deadline":
                DeadlineCommand.execute(command, taskList);
                break;

            case "event":
                EventCommand.execute(command, taskList);
                break;

            case "delete":
                taskList.deleteListItem(command);
                break;

            default:
                Ui.printLineSeparation();
                System.out.println("Please add a valid command:");
                System.out.println("- list");
                System.out.println("- todo <Task Description>");
                System.out.println("- deadline <Task Description> /by <Date>");
                System.out.println("- event <Task Description> /from <Date> /to <Date>");
                Ui.printLineSeparation();
                break;
        }
    }
}
