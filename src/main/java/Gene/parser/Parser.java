package Gene.parser;

import Gene.GeneException;
import Gene.command.DeadlineCommand;
import Gene.command.EventCommand;
import Gene.command.MarkCommand;
import Gene.command.TodoCommand;
import Gene.task.TaskList;
import Gene.ui.Ui;


/**
 * Represents the input parser of the Gene chatbot.
 * Execute the respective commands based on the parsed command.
 */
public class Parser {

    /**
     * Parses user input into the command type, and execute the respective command.
     *
     * @param taskList List of tasks added by user.
     * @param command User input to be parsed.
     * @throws GeneException if the user input is not part of the command list.
     */
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
