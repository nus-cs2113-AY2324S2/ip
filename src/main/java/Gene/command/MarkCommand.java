package Gene.command;

import Gene.GeneException;
import Gene.task.TaskList;


/**
 * Represents a command to mark tasks as done or undone.
 */
public class MarkCommand {

    /**
     * Check if an input string is a numerical string.
     *
     * @param str The string to be checked.
     * @return true if the string is numeric.
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Execute the mark command, marking the tasks as done or undone.
     *
     * @param command    User input of the mark command
     * @param taskList   User list of tasks.
     * @param markAsDone Boolean to represent to mark as done or undone.
     * @throws GeneException If there are any formatting issues.
     */
    public static void execute(String command, TaskList taskList, boolean markAsDone) throws GeneException {
        String[] parts = command.split(" ");
        if (parts.length < 2 || !isNumeric(parts[1])) {
            throw new GeneException("Please provide a valid task number." + System.lineSeparator()
                    + "Use Format: mark/unmark <task_number>");
        }
        int taskNumber = Integer.parseInt(parts[1]);

        if (markAsDone) {
            taskList.markTaskAsDone(taskNumber);
        } else {
            taskList.markTaskAsNotDone(taskNumber);
        }
    }
}
