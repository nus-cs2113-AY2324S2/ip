package utilities.commands;

import main.Aragorn;
import tasks.Task;
import ui.Constants;
import utilities.parser.InputParser;

public class CommandExecuter {

    /**
     * Receives the user input and executes the corresponding command.
     *
     * @param userInput User input typed on the console containing the task details.
     * @param commandType The command inputted by user.
     */
    public static void executeCommand(String userInput, String commandType) {

        try {
            InputParser input = new InputParser(userInput.trim(), commandType);
            int remainingTasks = 0;
            for (Task i : Aragorn.getList()) {
                if (i.getStatusIcon().equals(Constants.INCOMPLETE)) {
                    remainingTasks += 1;
                }
            }

            switch (commandType) {
                case Constants.LIST:
                    DisplayList.listCommand(Aragorn.getList());
                    Constants.printRemainingTasks(remainingTasks, Aragorn.getList().size());
                    break;

                case Constants.UNMARK:
                    UnmarkTask.unmarkTask(input, remainingTasks);
                    break;

                case Constants.MARK:
                    MarkTask.markTask(input, remainingTasks);
                    break;

                case Constants.DELETE:
                    DeleteTask.deleteTask(input, remainingTasks);
                    break;

                case Constants.TODO:
                    AddTask.todoCommand(input, remainingTasks);
                    break;

                case Constants.DEADLINE:
                    AddTask.deadlineCommand(input, remainingTasks);
                    break;

                case Constants.EVENT:
                    AddTask.eventCommand(input, remainingTasks);
                    break;

                case Constants.HELP:
                    System.out.println(Constants.HELPMESSAGE);
                    break;

                case Constants.FIND:
                    FindTask.findTasks(input, Aragorn.getList());
                    break;

                case Constants.INVALID:
                    if (userInput.trim().isEmpty()) {
                        System.out.println(Constants.EMPTYINPUT);
                    } else {
                        System.out.println(Constants.INVALIDINPUT);
                    }
                    break;
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println(Constants.INVALIDINDEXFORMAT);
        }
    }
}
