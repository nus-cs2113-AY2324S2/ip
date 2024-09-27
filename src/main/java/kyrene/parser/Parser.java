package kyrene.parser;

import kyrene.command.Command;
import kyrene.command.Commands;
import kyrene.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deals with making sense of the user command.
 * For example, converting user input strings to command-type objects, or to dates and times.
 */
public class Parser {

    /**
     * Returns Command-type object converted from the input string.
     * If the command is invalid, then throws exceptions to indicate that the input is invalid.
     *
     * @param sentence single line string to be parsed.
     * @return Command-type object.
     * @throws NumberFormatException If letters from the string cannot be converted to integers.
     * @throws ArrayIndexOutOfBoundsException If the string is an invalid command that only contains one or zero word.
     * @throws StringIndexOutOfBoundsException If the string is an invalid command.
     * that only contains the command word but no descriptions.
     */
    public static Command parse(String sentence) throws NumberFormatException, ArrayIndexOutOfBoundsException, StringIndexOutOfBoundsException {
        String[] commands = sentence.split(" ");
        String command = commands[0].toLowerCase();
        int taskNumber;

        switch (command) {
        case "bye":
            return new Command(Commands.BYE);
        case "list":
            return new Command(Commands.LIST);
        case "mark":
            taskNumber = Integer.parseInt(commands[1]);
            return new Command(Commands.MARK, taskNumber);
        case "unmark":
            taskNumber = Integer.parseInt(commands[1]);
            return new Command(Commands.UNMARK, taskNumber);
        case "delete":
            taskNumber = Integer.parseInt(commands[1]);
            return new Command(Commands.DELETE, taskNumber);
        case "at":
            return new Command(Commands.AT, sentence.substring("at ".length()));
        case "due":
            return new Command(Commands.DUE, sentence.substring("due ".length()));
        case "find":
            return new Command(Commands.FIND, commands[1]);
        default:
            return new Command(Commands.ADD, sentence);
        }
    }


    /**
     * Returns a word indicating the type of the task from the input string.
     *
     * @param sentence single line string to be parsed.
     * @return String indicating the type of the task.
     */
    public static String parseTaskType(String sentence) {
        String[] words = sentence.split(" ");
        return words[0];
    }


    /**
     * Returns a date from the input string.
     * If the string is not formatted properly, it requires another input of correct format.
     *
     * @param sentence single line string to be parsed.
     * @return A date.
     */
    public static LocalDate parseDate(String sentence) {
        LocalDate date;
        try {
            date = LocalDate.parse(sentence, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            Ui.showErrorInvalidDateFormat();
            sentence = Ui.readCommand();
            return parseDate(sentence);
        }
        return date;
    }

    /**
     * Returns a date with time from the input string.
     * If the string is not formatted properly, it tries to parse with date only.
     *
     * @param sentence single line string to be parsed.
     * @return A date with time.
     */
    public static LocalDateTime parseTime(String sentence) {
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(sentence, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            return parseTimeWithoutTime(sentence);
        }
        return time;
    }

    /**
     * Returns a date with appended time of 2359 from the input string that only contains date information.
     * If the string is not formatted properly, it tries to parse with time only.
     *
     * @param sentence single line string to be parsed that only contains date information.
     * @return A date with time.
     */
    private static LocalDateTime parseTimeWithoutTime(String sentence) {
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(sentence + " 2359", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            return parseTimeWithoutDate(sentence);
        }
        return time;
    }

    /**
     * Returns today's date with time from the input string that only contains time information.
     * If the string is not formatted properly, it requires another input of correct format.
     *
     * @param sentence single line string to be parsed that only contains time information.
     * @return A date with time.
     */
    private static LocalDateTime parseTimeWithoutDate(String sentence) {
        LocalDateTime time;
        try {
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            time = LocalDateTime.parse(today + " " + sentence, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            Ui.showErrorInvalidTimeFormat();
            sentence = Ui.readCommand();
            return parseTime(sentence);
        }
        return time;
    }

}
