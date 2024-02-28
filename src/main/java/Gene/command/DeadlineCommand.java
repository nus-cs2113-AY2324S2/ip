package Gene.command;

import Gene.task.Deadline;
import Gene.GeneException;
import Gene.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a command to add deadlines to the task list.
 */
public class DeadlineCommand {

    /**
     * Execute the deadline command, adding the deadline task into
     * the user task list.
     *
     * @param command User input of the dateline command.
     * @param taskList User list of task.
     * @throws GeneException if there is any formatting issues.
     */
    public static void execute(String command, TaskList taskList) throws GeneException {
        String[] parts = command.replaceFirst("\\S+", "").split("/by");
        if (parts.length < 2) {
            throw new GeneException("Invalid deadline format." + System.lineSeparator()
                    + "Use format: deadline <Task Description> /by <yyyy-MM-dd HHmm>");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        LocalDateTime deadlineDateTime = parseDeadlineDateTime(by);

        Deadline newDeadline = new Deadline(description, deadlineDateTime);
        taskList.addTask(newDeadline);
    }

    private static LocalDateTime parseDeadlineDateTime(String by) throws GeneException {
        try {
            // Parse date and time
            LocalDateTime deadlineDateTime = LocalDateTime.parse(by, DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HHmm"));
            return deadlineDateTime;
        } catch (Exception e) {
            throw new GeneException("Error parsing deadline date and time. " +
                    "Please use format: yyyy-MM-dd HHmm");
        }
    }
}
