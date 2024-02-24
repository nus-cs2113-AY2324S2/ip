package bossman.command;

import bossman.ui.Ui;
import bossman.exceptions.commandexceptions.InvalidDeadlineCommandException;
import bossman.task.Deadline;
import bossman.task.Task;
import bossman.task.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    private TaskList userTasks;
    private String taskDescription;
    private LocalDate by;

    public DeadlineCommand(TaskList userTasks, String commandArgs) throws InvalidDeadlineCommandException {
        this.userTasks = userTasks;

        String[] deadlineArgParts = commandArgs.split("/by", 2);

        if (deadlineArgParts.length != 2 || deadlineArgParts[0].isBlank()) {
            throw new InvalidDeadlineCommandException("Invalid deadline command");
        }

        taskDescription = deadlineArgParts[0];

        try {
            by = LocalDate.parse(deadlineArgParts[1].trim());
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineCommandException("Invalid deadline date");
        }
    }

    @Override
    public void execute() {
        Task deadlineTask = new Deadline(taskDescription, false, by);
        userTasks.addTask(deadlineTask);

        Ui.printMessageNoSepSameLine("Added:");
        deadlineTask.printTask();
        Ui.printMessageNoSepNewLine("");
        Ui.printMessageWithSepNewLine("Now you have " + userTasks.getTaskListSize() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}