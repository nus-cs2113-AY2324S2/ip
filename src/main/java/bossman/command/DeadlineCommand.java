package bossman.command;

import bossman.ui.Ui;
import bossman.exceptions.commandexceptions.InvalidDeadlineCommandException;
import bossman.task.Deadline;
import bossman.task.Task;
import bossman.task.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * DeadlineCommand represents the command to add a deadline task.
 * It implements the Command interface.
 */
public class DeadlineCommand implements Command {
    private TaskList userTasks;
    private String taskDescription;
    private LocalDate by;

    /**
     * Constructs a DeadlineCommand with the provided TaskList and command arguments.
     *
     * @param userTasks the TaskList containing user's tasks
     * @param commandArgs the arguments provided with the command
     * @throws InvalidDeadlineCommandException if the command arguments are invalid
     */
    public DeadlineCommand(TaskList userTasks, String commandArgs) throws InvalidDeadlineCommandException {
        this.userTasks = userTasks;

        // Split commandArgs into task description and deadline date parts
        String[] deadlineArgParts = commandArgs.split("/by", 2);

        // Check if the command arguments are valid
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

    /**
     * Executes the DeadlineCommand by adding the deadline task to the TaskList.
     * Prints a confirmation message with the added task and the updated task count.
     */
    @Override
    public void execute() {
        Task deadlineTask = new Deadline(taskDescription, false, by);
        userTasks.addTask(deadlineTask);

        Ui.printMessageNoSepSameLine("Added:");
        deadlineTask.printTask();
        Ui.printMessageNoSepNewLine("");
        Ui.printMessageWithSepNewLine("Now you have " + userTasks.getTaskListSize() + " tasks in the list.");
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true, indicating that this command is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}