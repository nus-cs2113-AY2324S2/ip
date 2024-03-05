import java.util.ArrayList;
import ChelleCommands.CommandType;
import ChelleCommands.HandleCommand;
import ChelleCommands.Task;
import ChelleExceptions.InvalidCommandFormatException;
import Common.Messages;
import Storage.ChelleStorage;

public class ChelleMain {
    public static void main(String[] args) {

        ChelleUI ui = new ChelleUI();
        ChelleStorage storage = new ChelleStorage();

        // Load tasks from the save file when the chatbot starts up
        ArrayList<Task> tasks = storage.loadTasks();

        while (true) {
            System.out.print(Messages.MESSAGE_INPUT_PREFIX);
            String userInput = ui.getUserInput();
            CommandType userCommand;
            try {
                userCommand = CommandType.valueOf((userInput.split(" ")[0]).toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println(Messages.MESSAGE_INVALID_COMMAND);
                continue;
            }

            switch (userCommand) {
            case BYE:
                storage.saveTasks(tasks);
                System.out.println(Messages.MESSAGE_BYE);
                ui.closeScanner();
                return;

            case LIST:
                // Fallthrough
            case MARK:
                // Fallthrough
            case UNMARK:
                // Fallthrough
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                // Fallthrough
            case DELETE:
                try {
                    HandleCommand.handleCommand(userInput, tasks, userCommand);
                } catch (InvalidCommandFormatException e) {
                    break;
                }
                break;
            default:
                System.out.println(Messages.MESSAGE_INVALID_COMMAND);
                break;
            }
        }
    }
}
