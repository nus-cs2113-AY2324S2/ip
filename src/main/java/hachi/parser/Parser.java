package hachi.parser;

import hachi.data.HachiException;
import hachi.data.TaskList;
import hachi.data.task.Task;
import hachi.data.task.TaskType;
import hachi.ui.Ui;

import java.util.ArrayList;

/**
 * This file represents the Parser class for the chatbot Hachi.
 * Parses user input in order to determine instructions for the chatbot.
 *
 * @author clarencepohh
 * @version 08/03/2024
 */

public class Parser {
    private final Ui ui;
    private final TaskList tasksList;

    /**
     * The constructor for the Parser class.
     * Initializes the required Ui and TaskList classes.
     *
     * @param ui The Ui class to be used for the Parser.
     * @param tasksList The TaskList class to be used for the Parser.
     */

    public Parser (Ui ui, TaskList tasksList) {
        this.ui = ui;
        this.tasksList = tasksList;
    }

    /**
     * Main Parser method that parses the user input and calls the required functions.
     *
     * @param firstWord The String that contains the first word in the users input.
     *                  Should contain the function keyword.
     * @param cleanedInput The String that contains the cleaned whole user input.
     * @param userInput The String the contains the whole user input.
     * @return The String that contains the parsed user command.
     * @throws HachiException If there is an error in any of the function calls.
     */

    public String processUserCommand (String firstWord, String cleanedInput, String userInput)
            throws HachiException {

        String userCommand = "notBye";

        switch (firstWord) {
        case "MARK":
        case "UNMARK":
            tasksList.markOrUnmarkHandler(cleanedInput);
            break;

        case "LIST":
            tasksList.retrieveTaskList();
            break;

        case "DELETE":
            tasksList.deleteTask(cleanedInput);
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

            tasksList.addTask(currentTask, userInput, cleanedInput);
            break;

        case "FIND":
            ArrayList<Task> foundTasksList = tasksList.findTask(cleanedInput);
            ui.printFoundTasks(foundTasksList);
            break;

        case "HELP":
            ui.printHelpMessage();
            break;

        case "BYE":
        case "GOODBYE":
            ui.printGoodbyeMessage();
            userCommand = "BYE";
            break;

        default:
            HachiException.invalidInput();
            break;
        }

        return userCommand;
    }

    /**
     * Given a String that contains the user input and the index of the first 'space' character in the user input,
     * returns the first word in the user input.
     *
     * @param indexOfSpace The int contains the index of the first 'space' character.
     * @param cleanedInput The String containing the cleaned whole user input.
     * @return The String containing the first word in the user input.
     */

    public String getFirstWordOfInput(int indexOfSpace, String cleanedInput) {
        String firstWord;
        if (indexOfSpace == -1) { // check for single-word inputs
            firstWord = cleanedInput;
        } else {
            firstWord = cleanedInput.substring(0, indexOfSpace);
        }
        return firstWord;
    }

    /**
     * Function is called from a delete request.
     * Given a String that contains the cleaned user input,
     * finds the int in the user input that specifies the index of the Task to be deleted.
     *
     * @param cleanedInput The String containing the cleaned whole user input.
     * @return The int that contains the index of the Task to be deleted.
     * @throws HachiException If the input is invalid.
     */

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

    /**
     * Function is called from a mark/unmark request.
     * Given a String that contains the cleaned user input,
     * finds the int in the user input that specifies the index of the Task to be marked/unmarked.
     *
     * @param cleanedInput The String containing the cleaned whole user input.
     * @return The int that contains the index of the Task to be marked/unmarked.
     * @throws HachiException If the input is invalid.
     */

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
