import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * The Parser class is responsible for interpreting user commands and parsing strings into tasks and their attributes.
 */
public class Parser {

    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")
    );

    /**
     * Parses the task number from the user input.
     * @param input The command entered by the user.
     * @return The task number as an integer.
     * @throws AliceException If the input does not contain a valid integer task number.
     */
    public int parseTaskNumber(String input) throws AliceException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new AliceException("Please enter a valid task number.");
        }
    }

    /**
     * Extracts the description from a user input string.
     * @param input The command entered by the user.
     * @return The description as a string.
     * @throws AliceException If the description is empty.
     */
    public String parseDescription(String input) throws AliceException {
        String description = input.substring(input.indexOf(" ") + 1);
        if (description.isBlank()) {
            throw new AliceException("Description cannot be empty.");
        }
        return description;
    }

    public static class DeadlineData {
        String description;
        String by;

        // Constructor
        public DeadlineData(String description, String by) {
            this.description = description;
            this.by = by;
        }
    }

    /**
     * Parses the given input to extract a deadline description and due date.
     *
     * @param input The input string to parse.
     * @return DeadlineData containing the description and the due date.
     * @throws AliceException If the input format is invalid.
     */

    public DeadlineData parseDeadline(String input) throws AliceException {
        String[] parts = input.split(" /by ", 2);
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new AliceException("Invalid deadline format. Please include '/by'.");
        }
        // No need to parse LocalDateTime here. Just pass the string directly.
        String description = parts[0].substring(parts[0].indexOf(" ") + 1);
        String by = parts[1]; // Pass the date string directly
        return new DeadlineData(description, by);
    }

    public static class EventData {
        String description;
        String start;
        String end;

        EventData(String description, String start, String end) {
            this.description = description;
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Parses an event command input to create an EventData object.
     * @param input The command entered by the user.
     * @return An EventData object containing the description, start, and end attributes.
     * @throws AliceException If the event command is formatted incorrectly.
     */
    public EventData parseEvent(String input) throws AliceException {
        if (!input.contains("/to") || !input.contains("/from")) {
            throw new AliceException("ayo event commands must include '/from' and '/to' so that i'll know when your event starts and ends yea?");
        }

        String[] parts = input.split(" /from ", 2);
        if (!parts[1].contains(" /to ")) {
            throw new AliceException("ayo the event time must be specified with '/from' followed by '/to' yea?");
        }

        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new AliceException("ayo do specify both the start and end times for your event yea?");
        }

        String description = parts[0].substring(parts[0].indexOf(" ") + 1);
        String start = timeParts[0];
        String end = timeParts[1];

        return new EventData(description, start, end);
    }

    /**
     * Parses a line of text into a Task object.
     * @param line The line of text representing a task.
     * @return A Task object.
     * @throws AliceException If the task type is unknown or the line is formatted incorrectly.
     */
    public static Task parseTask(String line) throws AliceException {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        Task task;

        switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                String byString = parts[3];
                task = new Deadline(parts[2], byString);
                break;

            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new AliceException("Unknown task type in the save file");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Attempts to parse the given date-time string into a LocalDateTime object.
     * Tries multiple formatters based on different expected date formats.
     *
     * @param dateTimeStr The date-time string to parse.
     * @return The LocalDateTime object if parsing is successful.
     * @throws AliceException If all formatters fail to parse the given string.
     */

    private static LocalDateTime parseDateTime(String dateTimeStr) throws AliceException {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTimeStr, formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next format
            }
        }
        throw new AliceException("Invalid date-time format for deadline task.");
    }
}
