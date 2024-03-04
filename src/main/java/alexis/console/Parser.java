package alexis.console;

import alexis.exception.MissingFieldException;
import alexis.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The Parser class is responsible for parsing user inputs and extracting relevant information
 * for further processing by the application.
 */
public class Parser {

    /**
     * Processes the user input from the console and performs actions based on the parsed commands.
     *
     * @param tasks The task list to operate on.
     * @param in The scanner used to read the user input.
     */
    public static void processUserInput(TaskList tasks, Scanner in) {
        while (true) {
            try {
                String line = in.nextLine();
                Command command = Parser.parseCommand(line);

                if (command == null) {
                    continue;
                }
                switch (command) {
                case BYE:
                    return;
                case LIST:
                    Ui.printTaskListToConsole(tasks);
                    break;
                case MARK:
                    Ui.printMarkedItemToConsole(tasks, line);
                    Storage.saveToLocalDisk(tasks);
                    break;
                case UNMARK:
                    Ui.printUnmarkedItemToConsole(tasks, line);
                    Storage.saveToLocalDisk(tasks);
                    break;
                case FIND:
                    Ui.printMatchingTasksToConsole(tasks, line);
                    break;
                case DELETE:
                    Ui.printDeletedTaskToConsole(tasks, line);
                    Storage.saveToLocalDisk(tasks);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Ui.printNewTaskToConsole(tasks, line, command);
                    Storage.saveToLocalDisk(tasks);
                    break;
                }
            } catch (MissingFieldException e) {
                Ui.printDescriptionErrorMessage();
            }
        }
    }

    /**
     * Parses the first word of the user's input and returns the command to be executed.
     *
     * @param input The input entered by the user.
     * @return The Command corresponding to the first word of the input or null if command does not exist.
     */
    public static Command parseCommand(String input) {
        String firstWord = getFirstWord(input);
        try {
            return Command.valueOf(firstWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            Ui.printCommandErrorMessage();
            return null;
        }
    }

    /**
     * Extracts and returns the first word from the user's input.
     * If the input contains no spaces, the whole input string is taken as the first word.
     *
     * @param input The input entered by the user.
     * @return The first word of the input as a String.
     */
    private static String getFirstWord(String input) {
        int firstSpace = input.indexOf(" ");
        if (firstSpace == -1) {
            return input;
        } else {
            return input.substring(0, firstSpace).trim();
        }
    }

    /**
     * Parses the input to extract the description of a task and returns the description as a string.
     *
     * @param input The input entered by the user.
     * @return The description part of the input.
     * @throws MissingFieldException If the description is missing.
     */
    public static String parseDescription(String input) throws MissingFieldException {
        int firstSpace = input.indexOf(" ");
        if (firstSpace == -1) {
            throw new MissingFieldException();
        }
        return input.substring(firstSpace + 1).trim();
    }

    public static String getDateTime(String input) {
        String dateInput = "yyyy-MM-dd HHmm";
        String dateOutput = "MMM dd yyyy, K.mm a";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(dateInput);

        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(dateOutput);
            return dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            return input;
        }
    }
}
