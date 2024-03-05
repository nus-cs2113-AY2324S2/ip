package soot.parser;

import soot.exceptions.EmptyTaskException;
import soot.exceptions.UnknownCommandException;
import soot.task.*;
import soot.ui.UserUi;

/**
 * Class Parser handles the input commands provided by the user,
 * by extracting the command action to ensure that the correct method is executed.
 */
public class Parser {

    /**
     * The command action word, i.e. the first word of the user input, is extracted.
     * Method handles the case where the user input only contains one word, which should only be "list".
     * Else, an EmptyTaskException is thrown to indicate to the user that the user input was incomplete.
     *
     * @param input command inputted by the user.
     * @return string of the command action word, is only one word long.
     * @throws EmptyTaskException If user input only contains one word that is not "list".
     */
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

    /**
     * Given the command action word, the command by the user will be handled.
     *
     * @param input command inputted by the user.
     */
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
                TaskList.addTask(input, TaskType.TODO);
                break;
            case "deadline":
                TaskList.addTask(input, TaskType.DEADLINE);
                break;
            case "event":
                TaskList.addTask(input, TaskType.EVENT);
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

    /**
     * Method to handle when user is trying to mark a task done or unmark.
     * The listIndex of this task is extracted.
     *
     * @param userInput command inputted by the user.
     * @param modifyCommand string that indicates done or unmark to determine how the task will be handled.
     * @throws IllegalArgumentException If the task specified by the user input is in the wrong format.
     */
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

    /**
     * Method to handle when user is trying to delete a task.
     * The listIndex of this task is extracted.
     *
     * @param userInput command inputted by the user.
     */
    private static void handleDeleteCommand(String userInput) {
        String inputTask = userInput.substring(7);
        int listIndex = Integer.parseInt(inputTask) - 1;
        TaskList.deleteTask(listIndex);
    }
}