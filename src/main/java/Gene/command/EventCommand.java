package Gene.command;

import Gene.task.Event;
import Gene.GeneException;
import Gene.task.TaskList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command to add events to the task list.
 */
public class EventCommand {

    /**
     * Execute the event command, adding the event task into the user task list.
     *
     * @param command User input of the event command.
     * @param taskList User list of tasks.
     * @throws GeneException if there is any formatting issues
     */
    public static void execute(String command, TaskList taskList) throws GeneException {
        String[] parts = command.replaceFirst("\\S+", "").split("/");
        if (parts.length < 3) {
            throw new GeneException("Invalid event format." + System.lineSeparator() +
                    "Use Format: event <task_description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        }
        String description = parts[0].trim();
        String from = parts[1].replace("from", "").trim();
        String to = parts[2].replace("to", "").trim();

        // Parse the event start and end date and time
        LocalDateTime eventStartDateTime = parseDateTime(from);
        LocalDateTime eventEndDateTime = parseDateTime(to);

        Event newEvent = new Event(description, eventStartDateTime, eventEndDateTime);
        taskList.addTask(newEvent);
    }

    private static LocalDateTime parseDateTime(String dateTimeString) throws GeneException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (Exception e) {
            throw new GeneException("Error parsing date and time. Please use format: yyyy-MM-dd HHmm");
        }
    }
}

