package uwunzhe.handler;

import uwunzhe.exceptions.UwunzheException;
import uwunzhe.util.TaskList;

public class Parser {
    private TaskList taskList;

    /**
     * Constructor for InputHandler.
     * 
     * @param taskList
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the input and calls the appropriate function.
     * 
     * @param input The input from the user.
     * @return If the task list was updated.
     * @throws UwunzheException
     */
    public boolean parseInput(String input) throws UwunzheException {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0].toLowerCase();
        String taskString = splitInput.length > 1 ? splitInput[1] : "";
        
        return parseCommand(command, taskString);
    }

    /**
     * Parses the command and calls the appropriate function.
     * 
     * @param taskList The list of tasks.
     * @param command The command from the user.
     * @param taskString The task from the user.
     * @return If the task list was updated.
     * @throws UwunzheException
     */
    public boolean parseCommand (String command, String taskString)
            throws UwunzheException {
        boolean isUpdated = false;

        switch (command) {
        case "list":
            // Print the list if input is "list"
            this.taskList.printList();
            break;

        case "mark":
        case "unmark":
            this.taskList.setItemStatus(command, taskString);
            isUpdated = true;
            break;

        case "todo":
        case "deadline":
        case "event":
            this.taskList.addItem(command, taskString);
            isUpdated = true;
            break;

        case "delete":
            this.taskList.deleteItem(taskString);
            isUpdated = true;
            break;

        default:
            // Add message for invalid input
            throw new UwunzheException("OH NO! I cannot understand!");
        }
        
        return isUpdated;
    }
}
