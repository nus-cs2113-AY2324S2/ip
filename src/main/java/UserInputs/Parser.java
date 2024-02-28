package UserInputs;

import Exceptions.ThawException;
import FileManagerPackage.Storage;
import PrintMessages.UI;
import Tasks.Task;
import commands.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class is responsible for processing user input, creating and executing commands.
 */
public class Parser {
    /**
     * This function is called after the program boot-up to continuously process user input.
     *
     * @param input the user's input from the terminal
     * @param list ArrayList that is initialised previously and passed onto this function to be populated
     * @param ui used to print messages based on the user's input
     * @throws ThawException customised error handling exception
     */
    public static void startListening(Scanner input, ArrayList<Task> list, UI ui) throws ThawException {
        boolean canExit = false;
        while (!canExit) {
            String usersInput = input.nextLine();

            if (usersInput.strip().equals("bye")) {
                canExit = true;
                ui.printGoodByeMessage();
            } else if (usersInput.strip().equals("list")) {
                ui.printList(list);
            } else {
               editTask(usersInput.strip(), list, ui);
            }
        }
    }

    /**
     * Edits tasks based on user input by creating and executing the corresponding command.
     *
     * @param usersInput The user's input from the terminal.
     * @param task       The ArrayList of tasks.
     * @param ui         The UI object for printing messages.
     */
    private static void editTask(String usersInput, ArrayList<Task> task, UI ui) {
        try {
            String firstWord = usersInput.split("\\s+")[0];
            Command commandTask = createCommandTask(firstWord, task, usersInput);
        } catch (ThawException e) {
            ui.handleError(e);
        }
    }

    /**
     * Creates a command task based on the user's input command.
     *
     * @param command    The command keyword extracted from the user's input.
     * @param task       The ArrayList of tasks.
     * @param usersInput The user's input from the terminal.
     * @return Command   The instantiated command task.
     * @throws ThawException Customized error handling exception.
     */
    private static Command createCommandTask(String command, ArrayList<Task> task, String usersInput) throws ThawException {
        switch (command) {
            case "mark":
                return new MarkTask(task, usersInput);
            case "unmark":
                return new UnmarkTask(task, usersInput);
            case "delete":
                return new DeleteTask(task, usersInput);
            case "todo":
                return new AddTodoTask(task, usersInput);
            case "deadline":
                return new AddDeadlineTask(task, usersInput);
            case "event":
                return new AddEventTask(task, usersInput);
            case "find":
                return new FindTask(task, usersInput);
            default:
                throw new ThawException("Invalid command");
        }
    }

    /**
     * Process user's input string to get a date formatted variable.
     * Used for reading the data in a saved file in the Storage Class.
     * @param inputDateTimeString string that is read from the saved file
     * @return LocalDate a class from java.time package
     */
    public static LocalDate processDate(String inputDateTimeString) {
        String inputDateString = inputDateTimeString.strip();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return LocalDate.parse(inputDateString, inputFormatter);
    }

    /**
     * Process user's input string to get a time formatted variable.
     * Used for reading the data in a saved file in the Storage Class.
     * @param inputDateTimeString string that is read from the saved file
     * @return LocalTime a class from java.time package
     */
    public static LocalTime processTime(String inputDateTimeString) {
        String inputTimeString = inputDateTimeString.strip();
        DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(inputTimeString, inputTimeFormatter);
    }
}
