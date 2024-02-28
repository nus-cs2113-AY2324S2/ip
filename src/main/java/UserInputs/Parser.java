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

public class Parser {
    /**
     * This function is to be called after the program boot up and print the acknowledgement message
     * Contionously run to process user's input
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
     * Process user's inputs that are commands which edit the existing list of task
     * @param usersInput the string for user's input
     * @param task ArrayList of task
     * @param ui used to print error messages
     */
    private static void editTask(String usersInput, ArrayList<Task> task, UI ui) {
        try {
            String firstWord = usersInput.split("\\s+")[0];
            Command commandTask;
            switch (firstWord) {
                case "mark":
                    commandTask = new MarkTask(task, usersInput);
                    break;
                case "unmark":
                    commandTask = new UnmarkTask(task, usersInput);
                    break;
                case "delete":
                    commandTask = new DeleteTask(task, usersInput);
                    break;
                case "todo":
                    commandTask = new AddTodoTask(task, usersInput);
                    break;
                case "deadline":
                    commandTask = new AddDeadlineTask(task, usersInput);
                    break;
                case "event":
                    commandTask = new AddEventTask(task, usersInput);
                    break;
                default:
                    throw new ThawException("Invalid command");
            }
        } catch (ThawException e) {
            ui.handleError(e);
        }
    }

    /**
     * Process userinput string to get a date formatted variable.
     * Used for reading the data in a saved file in the Storage Class.
     * @param inputDateTimeString string that is read from the saved file
     * @return
     */
    public static LocalDate processDate(String inputDateTimeString) {
        String inputDateString = inputDateTimeString.strip();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return LocalDate.parse(inputDateString, inputFormatter);
    }

    public static LocalTime processTime(String inputDateTimeString) {
        String inputTimeString = inputDateTimeString.strip();
        DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(inputTimeString, inputTimeFormatter);
    }
}
