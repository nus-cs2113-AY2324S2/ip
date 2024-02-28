package Gene.command;

import Gene.task.Deadline;
import Gene.GeneException;
import Gene.task.TaskList;

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
        String[] parts = command.replaceFirst("\\S+", "").split("/");
        if (parts.length < 2) {
            throw new GeneException("Invalid deadline format." + System.lineSeparator()
                    + "Use format: deadline <Task Description> /by <Date>");
        }
        Deadline newDeadline = new Deadline(parts[0].trim(), parts[1]
                .replace("by", "").trim());
        taskList.addTask(newDeadline);
    }
}
