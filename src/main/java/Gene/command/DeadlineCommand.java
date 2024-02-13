package Gene.command;

import Gene.task.Deadline;
import Gene.GeneException;
import Gene.task.TaskList;

public class DeadlineCommand {
    public static void execute(String command, TaskList taskList) throws GeneException {
        String[] parts = command.replaceFirst("\\S+", "").split("/");
        if (parts.length < 2) {
            throw new GeneException("Invalid deadline format." + System.lineSeparator()
                    + "Use format: deadline (Task Description) /by (Date)");
        }
        Deadline newDeadline = new Deadline(parts[0].trim(), parts[1]
                .replace("by", "").trim());
        taskList.addTask(newDeadline);
    }
}
