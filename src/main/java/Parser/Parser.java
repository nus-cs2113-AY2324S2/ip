package Parser;

import ChelleCommands.CommandType;
import ChelleCommands.TaskList;
import ChelleCommands.Task;
import ChelleExceptions.InvalidCommandFormatException;
import Common.Messages;
import Storage.ChelleStorage;
import UI.ChelleUI;

import java.util.ArrayList;

public class Parser {
    public boolean isByeCommandDetected = false;
    TaskList taskList = new TaskList();
    public Parser(){
    }

    public void parseInput(ArrayList<Task> tasks, String userInput, ChelleStorage storage, ChelleUI ui){
        CommandType userCommand = null;
        try {
            userCommand = CommandType.valueOf((userInput.split(" ")[0]).toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(Messages.MESSAGE_INVALID_COMMAND);
        }

        if (userCommand != null){
            switch (userCommand) {
            case BYE:
                storage.saveTasks(tasks);
                System.out.println(Messages.MESSAGE_BYE);
                ui.closeScanner();
                isByeCommandDetected = true;
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
                //Fallthrough
            case FIND:
                try {
                    taskList.handleCommand(userInput, tasks, userCommand);
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
