package soot.parser;

import soot.exceptions.EmptyTaskException;
import soot.exceptions.UnknownCommandException;
import soot.task.*;
import soot.ui.UserUi;

public class Parser {

    public static String getCommandAction(String input) throws EmptyTaskException {
        if (input.equals("list")) {
            return input;
        }

        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            throw new EmptyTaskException();
        }
        return input.substring(0, spaceIndex);
    }

    public static void parseCommand(String input) {
        try {
            String inputCommand = getCommandAction(input);
            String inputTask;
            switch (inputCommand) {
            case "list":
                TaskList.printList();
                break;
            case "done":
                handleModifyDoneCommands(input, "done");
                break;
            case "unmark":
                handleModifyDoneCommands(input, "unmark");
                break;
            case "delete":
                handleDeleteCommand(input);
                break;
            case "todo":
                TaskList.addTask(input, taskType.TODO);
                break;
            case "deadline":
                TaskList.addTask(input, taskType.DEADLINE);
                break;
            case "event":
                TaskList.addTask(input, taskType.EVENT);
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (EmptyTaskException e) {
            System.out.println("  !! hmmm, no task was specified.");
            UserUi.displayDividerLine();
        } catch (UnknownCommandException e) {
            System.out.println("  !! this isn't a command i recognise...\n  " +
                    "sorry, pls try again");
            UserUi.displayDividerLine();
        }
    }

    static void handleModifyDoneCommands(String userInput, String modifyCommand) throws IllegalArgumentException {
        String inputTask;
        int listIndex;
        try {
            if (modifyCommand.equals("done")) {
                inputTask = userInput.substring(5,6);
                listIndex = Integer.parseInt(inputTask) - 1;
                TaskList.markTaskDone(listIndex);
            } else if (modifyCommand.equals("unmark")) {
                inputTask = userInput.substring(7,8);
                listIndex = Integer.parseInt(inputTask) - 1;
                TaskList.markTaskUndone(listIndex);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("the task specified should be a integer task number! \n" +
                    "you can type command 'list' to verify this number :)");
            UserUi.displayDividerLine();
        }
    }

    private static void handleDeleteCommand(String userInput) {
        String inputTask = userInput.substring(7);
        int listIndex = Integer.parseInt(inputTask) - 1;
        TaskList.deleteTask(listIndex);
    }
}