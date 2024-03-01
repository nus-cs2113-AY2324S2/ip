package Parser;

import NewExceptions.EmptyArgumentException;
import NewExceptions.EmptyStatementException;

import Tasking.TaskList;
import Tasking.Todo;
import Tasking.Davvy;
import Ui.Ui;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Parser {

    /**
     * Takes in the string that comes after the 'deadline' command, and processes it to
     * obtain the 'by' date
     *
     * @param commandArg String that comes after the keyword 'deadline'
     * @throws IOException if there is an issue writing to file
     */
    private static void handleDeadline(String commandArg) throws IOException {
        String[] newCommandArg = commandArg.split("/by", 2);
        Deadline inputDeadline = new Deadline(newCommandArg[0], newCommandArg[1]);
        TaskList.addTask(inputDeadline,false);
    }

    /**
     * Takes in the string that comes after the 'deadline' command, and processes it to
     * obtain the 'from' and 'to' parameters
     *
     * @param commandArg String that comes after the keyword 'event'
     * @throws IOException if there is an issue writing to file
     */
    private static void handleEvent(String commandArg) throws IOException {
        String[] newCommandArg = commandArg.split("/from", 2);
        String[] newCommandArg2 = newCommandArg[1].split("/to", 2);
        Events inputEvent = new Events(newCommandArg[0], newCommandArg2[0], newCommandArg2[1]);
        TaskList.addTask(inputEvent, false);
    }

    /**
     * Cleans up raw user input, and returns an array of 2 strings, where index 0 is command type,
     * index 1 is command argument
     *
     * @param inputText Pass in the raw user input
     * @return An array of 2 strings
     * @throws EmptyStatementException if the user input was entirely empty
     */
    public static String[] processInput (String inputText) throws EmptyStatementException {
        // String processing, \\b is used to signal the end of a word
        String[] processedInput = inputText.split("\\b", 2);
        if (inputText.isEmpty()) {
            throw new EmptyStatementException();
        }
        // String Cleaning
        processedInput[0] = processedInput[0].trim().toLowerCase();
        processedInput[1] = processedInput[1].isEmpty() ? "" : processedInput[1].trim();
        return processedInput;
    }

    /**
     * The method takes in a cleaned user input and further processes it based on the command
     * given
     *
     * @param input Pass in an array of 2 strings, string at index 0 gives the command type, string
     *              at index 1 gives the command argument
     * @return true if the chatbot is to be terminated, false otherwise
     * @throws EmptyArgumentException if there is nothing in the argument string when a command is
     *                                entered
     * @throws IOException if there was an issue reading/writing into the file
     */
    public static boolean processCommand (String[] input) throws EmptyArgumentException,
            IOException {
        String commandType = input[0];
        String commandArg = input[1];
        if (!commandType.equalsIgnoreCase("bye")) {
            Ui.printLine();
        }
        int taskIndex;

        switch (commandType) {
        case "list":
            TaskList.printList();
            break;
        case "mark":
            taskIndex = parseInt(commandArg.trim()) - 1;
            TaskList.getTask(taskIndex).markDone(true);
            break;
        case "unmark":
            taskIndex = parseInt(commandArg.trim()) - 1;
            TaskList.getTask(taskIndex).markNotDone(true);
            break;
        case "bye":
            return true;
        case "todo":
            if (commandArg.isEmpty()) {
                throw new EmptyArgumentException();
            }
            Todo inputTodo = new Todo(commandArg);
            TaskList.addTask(inputTodo, false);
            break;
        case "deadline":
            if (commandArg.contains("/by")) {
                handleDeadline(commandArg);
            } else {
                System.out.println("Please put a date!");
            }
            break;
        case "event":
            if (commandArg.contains("/from") && commandArg.contains("/to")) {
                handleEvent(commandArg);
            } else {
                System.out.println("Please put a correct time!");
            }
            break;
        case "delete":
            // This regex refers to all numbers
            if (commandArg.matches("[0-9]+")) {
                TaskList.deleteTask(Integer.parseInt(commandArg));
            } else {
                System.out.println("Please enter a number!");
            }
            break;
        case "find":
            TaskList.findList(commandArg);
            break;
        default:
            System.out.println("Unknown Command, type something I know please!");
            break;
        }
        return false;
    }
}
