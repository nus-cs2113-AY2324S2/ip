package uwunzhe;

import uwunzhe.util.Printer;
import uwunzhe.util.UserInput;
import uwunzhe.util.TaskList;


public class Uwunzhe {
    /**
     * Parses the user input and executes the corresponding command.
     * 
     * @param taskList The list of tasks.
     * @param command The command to execute.
     * @param taskString The task to execute the command on.
     * @return None
     */
    public static void parseCommand(TaskList taskList, String command, String taskString) {
        switch (command) {
        case "list":
            // Print the list if input is "list"
            taskList.printList();
            break;

        // TODO: Add support for catching non-integer input
        case "mark":
            // Mark a task as done if input is "mark"
            int index = Integer.parseInt(taskString);
            taskList.setItemStatus(index, true);
            break;
        case "unmark":
            // Mark a task as not done if input is "unmark"
            index = Integer.parseInt(taskString);
            taskList.setItemStatus(index, false);
            break;

        case "todo":
        case "deadline":
        case "event":
            taskList.addItem(taskString, command);
            break;

        default:
            // Add default task to the list (no type)
            taskList.addItem(command);
            break;
        }
    }

    /**
     * The main loop of the bot, handles user input.
     * 
     * @param None
     * @return None
     */
    public static void loop() {
        boolean isRunning = true;
        TaskList taskList = new TaskList();

        while (isRunning) {
            String input = UserInput.getInput();

            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0].toLowerCase();
            String taskString = splitInput.length > 1 ? splitInput[1] : "";

            if (command.equals("bye")) {
                // Exit loop if input is "bye"
                isRunning = false;
                continue;
            }
            parseCommand(taskList, command, taskString);
        }
        
        // Close the scanner at the end of the program
        UserInput.closeScanner();
    }

    public static void main(String[] args) {
        Printer.printInitMsg();
        loop();
        Printer.printExitMsg();
    }
}
