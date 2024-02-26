package hachi.parser;

import hachi.data.HachiException;
import hachi.data.TaskList;
import hachi.data.task.TaskType;
import hachi.ui.Ui;

/**
 * Parses user input in order to determine instructions for the chatbot.
 * To be completed
 */

public class Parser {
    private Ui ui;

    public Parser (Ui ui) {
        this.ui = ui;
    }

    public static String processUserCommand (String firstWord, String cleanedInput, String userInput)
            throws HachiException {

        String userCommand = "notBye";

        switch (firstWord) {
        case "MARK":
        case "UNMARK":
            TaskList.markOrUnmarkHandler(cleanedInput);
            break;

        case "LIST":
            TaskList.retrieveTaskList();
            break;

        case "DELETE":
            TaskList.deleteTask(cleanedInput);
            break;

        case "TODO":
        case "EVENT":
        case "DEADLINE":
            TaskType currentTask;

            if (cleanedInput.startsWith("EVENT")) {
                currentTask = TaskType.EVENT;
            } else if (cleanedInput.startsWith("DEADLINE")) {
                currentTask = TaskType.DEADLINE;
            } else {
                currentTask = TaskType.TODO;
            }

            TaskList.addTask(currentTask, userInput, cleanedInput);
            break;

        case "BYE":
        case "GOODBYE":
            Ui.printGoodbyeMessage();
            userCommand = "BYE";
            break;

        case "HELP":
            Ui.printHelpMessage();
            break;

        default:
            HachiException.invalidInput();
            break;
        }

        return userCommand;
    }

    public static String getFirstWordOfInput(int indexOfSpace, String cleanedInput) {
        String firstWord;
        if (indexOfSpace == -1) { // check for single-word inputs
            firstWord = cleanedInput;
        } else {
            firstWord = cleanedInput.substring(0, indexOfSpace);
        }
        return firstWord;
    }

    public static int getDeleteTaskNumber(String cleanedInput) throws HachiException {
        int indexOfTaskNum = cleanedInput.indexOf("DELETE") + 6; // find index of task number
        int taskNumber = 0;

        try {
            taskNumber = Integer.parseInt(cleanedInput.substring(indexOfTaskNum).trim()); // parse string to int
        } catch (NumberFormatException e){
            HachiException.checkOutOfBounds(indexOfTaskNum);
        }

        return taskNumber;
    }

    public static int getMarkTaskNumber(String cleanedInput) throws HachiException {
        int indexOfTaskNum = cleanedInput.indexOf("MARK") + 4; // find index of task number
        int taskNumber = 0;

        try {
            taskNumber = Integer.parseInt(cleanedInput.substring(indexOfTaskNum).trim()); // parse string to int
        } catch (NumberFormatException e){
            HachiException.checkOutOfBounds(indexOfTaskNum);
        }

        return taskNumber;
    }
}
