import Quokka.exceptions.QuokkaException;
import Quokka.tasks.Deadline;
import Quokka.tasks.Event;
import Quokka.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class handles the parsing of user input into task objects.
 */
public class Parser {

    /**
     * Parses user input to create a Todo task.
     *
     * @param userInput The user input string containing the description of the task.
     * @return A Todo object representing the parsed task.
     */
    public static Todo parseTodoTask(String userInput) {
        try {
            String description = userInput.substring("todo".length()).trim();
            if (description.isEmpty()) {
                throw new QuokkaException("Please provide a description for the todo task.");
            }
            return new Todo(description, false);
        } catch (QuokkaException e) {
            System.out.println("    Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Parses user input to create a Deadline task.
     *
     * @param userInput The user input string containing the description and deadline of the task.
     * @return A Deadline object representing the parsed task.
     * @throws QuokkaException If there is an error parsing the input.
     */
    public static Deadline parseDeadlineTask(String userInput) throws QuokkaException {
        try {
            String[] parts = userInput.split("/by", 2);
            if (parts.length == 2) {
                String description = parts[0].substring("deadline".length()).trim();
                String dateTimeString = parts[1].trim();
                LocalDateTime by = parseDateTime(dateTimeString);
                if (description.isEmpty()) {
                    throw new QuokkaException("Please provide a description for the deadline task.");
                }
                return new Deadline(description, by, false);
            } else {
                throw new QuokkaException("Invalid deadline format. Please use: deadline [description] /by [date/time]");
            }
        } catch (QuokkaException e) {
            System.out.println("    Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Parses a date/time string into a LocalDateTime object.
     *
     * @param dateTimeString The date/time string to parse.
     * @return A LocalDateTime object representing the parsed date/time.
     * @throws QuokkaException If there is an error parsing the date/time string.
     */
    private static LocalDateTime parseDateTime(String dateTimeString) throws QuokkaException {
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (Exception e) {
            throw new QuokkaException("Error parsing date/time. Please use format: dd/MM/yyyy HHmm");
        }
    }

    /**
     * Parses user input to create an Event task.
     *
     * @param userInput The user input string containing the description, start time, and end time of the event.
     * @return An Event object representing the parsed task.
     */
    public static Event parseEventTask(String userInput) {
        try {
            String[] parts = userInput.split("/from", 2);
            if (parts.length == 2) {
                String description = parts[0].substring("event".length()).trim();
                String[] dateTimes = parts[1].split("/to", 2);
                if (dateTimes.length == 2) {
                    String from = dateTimes[0].trim();
                    String to = dateTimes[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new QuokkaException("Please provide description, start time, and end time for the event task.");
                    }
                    return new Event(description, from, to, false);
                } else {
                    throw new QuokkaException("Invalid event format. Please use: event [description] /from [start] /to [end]");
                }
            } else {
                throw new QuokkaException("Invalid event format. Please use: event [description] /from [start] /to [end]");
            }
        } catch (QuokkaException e) {
            System.out.println("    Error: " + e.getMessage());
            return null;
        }
    }
}
