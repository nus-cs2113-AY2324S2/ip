package bossman.command;

import bossman.ui.Ui;
import bossman.exceptions.commandexceptions.InvalidDeadlineCommandException;
import bossman.task.Deadline;
import bossman.task.Task;
import bossman.task.TaskList;

public class DeadlineCommand implements Command {
    private TaskList userTasks;
    private String taskDescription;
    private String by;

    public DeadlineCommand(TaskList userTasks, String commandArgs) throws InvalidDeadlineCommandException {
        this.userTasks = userTasks;

        String[] deadlineArgParts = commandArgs.split("/by", 2);

        if (deadlineArgParts.length != 2 || deadlineArgParts[0].isBlank()) {
            throw new InvalidDeadlineCommandException("Invalid deadline command");
        }

        taskDescription = deadlineArgParts[0];
        by = deadlineArgParts[1];
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