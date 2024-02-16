package uwunzhe.handler;

import uwunzhe.exceptions.UwunzheException;
import uwunzhe.util.TaskList;

public class InputHandler {
    private TaskList taskList;

    /**
     * Constructor for InputHandler.
     * 
     * @param taskList
     */
    public InputHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the input and calls the appropriate function.
     * 
     * @param input The input from the user.
     * @return None
     * @throws UwunzheException
     */
    public void praseInput(String input) throws UwunzheException {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0].toLowerCase();
        String taskString = splitInput.length > 1 ? splitInput[1] : "";
        
        parseCommand(command, taskString);
    }

    /**
     * Parses the command and calls the appropriate function.
     * 
     * @param taskList The list of tasks.
     * @param command The command from the user.
     * @param taskString The task from the user.
     * @return None
     * @throws UwunzheException
     */
    public void parseCommand (String command, String taskString)
            throws UwunzheException {
        switch (command) {
        case "list":
            // Print the list if input is "list"
            this.taskList.printList();
            break;

        case "mark":
        case "unmark":
            this.taskList.setItemStatus(command, taskString);
            break;

        case "todo":
        case "deadline":
        case "event":
            this.taskList.addItem(command, taskString);
            break;

        case "delete":
            this.taskList.deleteItem(taskString);
            break;

        default:
            // Add message for invalid input
            throw new UwunzheException("OH NO! I cannot understand!");
        }
    }
}
