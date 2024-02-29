package kyrene.parser;

import kyrene.command.Command;
import kyrene.command.Commands;
import kyrene.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String sentence) throws NumberFormatException, IndexOutOfBoundsException {
        String[] commands = sentence.split(" ");
        String command = commands[0];
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
        default:
            return new Command(Commands.ADD, sentence);
        }
    }

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

    public static LocalDateTime parseTime(String sentence) {
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(sentence, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            return parseTimeWithoutTime(sentence);
        }
        return time;
    }

    private static LocalDateTime parseTimeWithoutTime(String sentence) {
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(sentence + " 2359", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            return parseTimeWithoutDate(sentence);
        }
        return time;
    }

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
