package soot.parser;

import soot.exceptions.EmptyTaskException;
import soot.exceptions.UnknownCommandException;

import soot.task.Task;
import soot.task.TaskType;
import soot.task.TaskList;

import soot.ui.UserUi;
import java.util.ArrayList;


/**
 * Handles the input commands provided by the user,
 * by extracting the command action to ensure that the correct method is executed.
 */
public class Parser {

    /**
     * Handle the command by the user, given the command action word.
     *
     * @param userInput command inputted by the user.
     */
    public static void parseCommand(String userInput) {
        try {
            String inputCommand = getCommandAction(userInput);
            switch (inputCommand) {
            case "list":
                TaskList.printList();
                break;
            case "done":
                handleModifyDoneCommands(userInput, "done");
                break;
            case "unmark":
                handleModifyDoneCommands(userInput, "unmark");
                break;
            case "delete":
                handleDeleteCommand(userInput);
                break;
            case "find":
                handleFindCommand(userInput);
                break;
            case "todo":
                TaskList.addTask(userInput, TaskType.TODO);
                break;
            case "deadline":
                TaskList.addTask(userInput, TaskType.DEADLINE);
                break;
            case "event":
                TaskList.addTask(userInput, TaskType.EVENT);
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (EmptyTaskException e) {
            UserUi.printMessageWithDivider("  !! hmmm, no task was specified.");
        } catch (UnknownCommandException e) {
            UserUi.printMessageWithDivider("  !! this isn't a command i recognise...\n"
                    + "sorry, pls try again");
        }
    }

    /**
     * Returns the command action word, i.e. the first word of the user input.
     * Method first handles the case where the user input only contains one word, which should only be "list".
     * Else, an EmptyTaskException is thrown to indicate to the user that the user input was incomplete.
     *
     * @param userInput command inputted by the user.
     * @return string of the command action word, is only one word long.
     * @throws EmptyTaskException If user input only contains one word that is not "list".
     */
    private static String getCommandAction(String userInput) throws EmptyTaskException {
        if (userInput.equals("list")) {
            return userInput;
        }

        int spaceIndex = userInput.indexOf(" ");
        if (spaceIndex == -1) {
            throw new EmptyTaskException();
        }
        return userInput.substring(0, spaceIndex);
    }

    /**
     * Handles when user is trying to mark a task done or unmark.
     * Note: There are two indexes used.
     * The index provided by the user is the index when "list" is called, it can be a value from 1.
     * taskIndexInArrayList is the index of this task in the Array List, it can be a value from 0.
     *
     * @param userInput command inputted by the user.
     * @param modifyCommandType string of done or unmark to determine how the task will be handled.
     * @throws IllegalArgumentException If the task specified by the user input is in the wrong format.
     */
    private static void handleModifyDoneCommands(String userInput, String modifyCommandType)
            throws IllegalArgumentException {
        String taskIndexInList;
        int taskIndexInArrayList;
        try {
            if (modifyCommandType.equals("done")) {
                taskIndexInList = userInput.substring(5,6);
                taskIndexInArrayList = Integer.parseInt(taskIndexInList) - 1;
                TaskList.markTaskDone(taskIndexInArrayList);
            } else if (modifyCommandType.equals("unmark")) {
                taskIndexInList = userInput.substring(7,8);
                taskIndexInArrayList = Integer.parseInt(taskIndexInList) - 1;
                TaskList.markTaskUndone(taskIndexInArrayList);
            } else {
                System.out.println("unknown modify command type. Should only be done or unmark.");
            }
        } catch (IllegalArgumentException e) {
            UserUi.printMessageWithDivider("the task specified should be a integer task number! \n" +
                    "you can type command 'list' to verify this number :)");
        }
    }

    /**
     * Handles when user is trying to delete a task.
     * Note: There are two indexes used.
     * The index provided by the user is the index when "list" is called, it can be a value from 1.
     * taskIndexInArrayList is the index of this task in the Array List, it can be a value from 0.
     *
     * @param userInput command inputted by the user.
     */
    private static void handleDeleteCommand(String userInput) {
        String taskIndexInList = userInput.substring(7);
        int taskIndexInArrayList = Integer.parseInt(taskIndexInList) - 1;
        TaskList.deleteTask(taskIndexInArrayList);
    }

    private static void handleFindCommand(String userInput) {
        ArrayList<Task> foundKeywordList = new ArrayList<Task>();
        String toFind = userInput.substring(5);
        foundKeywordList = TaskList.findKeyword(toFind);
        UserUi.printKeywordList(foundKeywordList);
    }
}