package gandalf;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class provides methods for parsing user inputs.
 */
public class Parser {
    private final String userInput;
    private String eventItem;
    private String eventFrom;
    private String eventTo;
    private String deadlineItem;
    private String deadlineDueBy;
    private String toDoItem;

    /**
     * Constructs a Parser object with the given user input.
     *
     * @param userInput The user input to parse.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
        parseInput();
    }

    /** Parses the input command and extracts relevant information for event, deadline, or todo tasks. */
    private void parseInput() {
        if (userInput.startsWith("event")) {
            String removeEventString = userInput.replaceFirst("event", "").trim();
            String[] firstPartition = removeEventString.split("/from");
            eventItem = firstPartition[0];
            String fromAndToString = firstPartition[1];
            String[] secondPartition = fromAndToString.split("/to");
            eventFrom = secondPartition[0];
            eventTo = secondPartition[1];
        }
        else if (userInput.startsWith("deadline")) {
            String removeDeadlineString = userInput.replaceFirst("deadline", "").trim();
            String[] parts = removeDeadlineString.split("/by");
            deadlineItem = parts[0];
            deadlineDueBy = parts[1].trim();
        }
        else {
            toDoItem = userInput.substring(4).trim();
        }
    }

    /**
     * Parses the index from the user input for delete, mark, or unmark commands.
     *
     * @param userInput The user input containing the index.
     * @return The index parsed from the user input.
     */
    public static int parseIndex(String userInput) {
        if (userInput.startsWith("delete")) {
            return Integer.parseInt(userInput.substring(6).trim());
        } else if (userInput.startsWith("mark")) {
            return Integer.parseInt(userInput.substring(5).trim());
        } else {
            // unmark
            return Integer.parseInt(userInput.substring(7).trim());
        }
    }

    /**
     * Parses the previous task from the provided line read from save-file.txt.
     *
     * @param line The line read from storage containing task information.
     * @return The parsed previous task.
     */
    public static String parsePreviousTask(String line) {
        String parsedTask = null;
        if (line.startsWith("[T]")) {
            // Todo task
            parsedTask = "todo " + line.substring(6).trim();
        } else if (line.startsWith("[D]")) {
            // Deadline task
            int endIndex = line.indexOf("(by:");
            String description = line.substring(6, endIndex).trim();
            String deadline = line.substring(endIndex + 5, line.length() - 1).trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate date = LocalDate.parse(deadline, formatter);
            parsedTask = "deadline " + description + " /by " + date;
        } else if (line.startsWith("[E]")) {
            // Event task
            int fromIndex = line.indexOf("(from:") + 6;
            int toIndex = line.indexOf("to:");
            String description = line.substring(6, fromIndex - 7).trim();
            String fromTime = line.substring(fromIndex, toIndex - 1).trim();
            String toTime = line.substring(toIndex + 4, line.length() - 1).trim();
            parsedTask = "event " + description + " /from " + fromTime + " /to " + toTime;
        }
        return parsedTask;
    }

    /**
     * Parses the task to find from the user input.
     *
     * @param userInput The user input containing the find command.
     * @return The task to find.
     */
    public static String parseTaskToFind(String userInput) {
        return userInput.replaceFirst("find","").trim();
    }

    /**
     * Gets the event item parsed from the user input.
     *
     * @return The event item.
     */
    public String getEventItem() {
        return eventItem;
    }

    /**
     * Gets the event start date/time parsed from the user input.
     *
     * @return The event start date/time.
     */
    public String getEventFrom() {
        return eventFrom;
    }

    /**
     * Gets the event end time parsed from the user input.
     *
     * @return The event end time.
     */
    public String getEventTo() {
        return eventTo;
    }

    /**
     * Gets the deadline item parsed from the user input.
     *
     * @return The deadline item.
     */
    public String getDeadlineItem() {
        return deadlineItem;
    }

    /**
     * Gets the deadline due by date parsed from the user input.
     *
     * @return The deadline due by date.
     */
    public String getDeadlineDueBy() {
        return deadlineDueBy;
    }

    /**
     * Gets the todo item parsed from the user input.
     *
     * @return The todo item.
     */
    public String getToDoItem() {
        return toDoItem;
    }
}
