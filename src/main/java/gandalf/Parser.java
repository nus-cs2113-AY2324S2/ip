package gandalf;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private final String userInput;
    private String eventItem;
    private String eventFrom;
    private String eventTo;
    private String deadlineItem;
    private String deadlineDueBy;
    private String toDoItem;


    public Parser(String userInput) {
        this.userInput = userInput;
        parseInput();
    }

    public static String parseTaskToFind(String userInput) {
        return userInput.replaceFirst("find","").trim();
    }

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

    public String getEventItem() {
        return eventItem;
    }

    public String getEventFrom() {
        return eventFrom;
    }

    public String getEventTo() {
        return eventTo;
    }

    public String getDeadlineItem() {
        return deadlineItem;
    }

    public String getDeadlineDueBy() {
        return deadlineDueBy;
    }

    public String getToDoItem() {
        return toDoItem;
    }

    static String parsePreviousTask(String line) {
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

}
