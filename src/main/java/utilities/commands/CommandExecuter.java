package utilities.commands;

import main.Aragorn;
import tasks.Task;
import ui.Constants;
import utilities.file.InputParser;

public class CommandExecuter {
    public static void ExecuteCommand(String userInput, String commandType) {

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
