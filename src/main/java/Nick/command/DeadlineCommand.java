package Nick.command;

import Nick.storage.Storage;
import Nick.task.TaskList;
import Nick.ui.Ui;
import Nick.task.Deadline;
import Nick.task.Task;

/**
 * Represents the Deadline command which inherits from the Command class.
 * It includes the execute method which adds a Deadline task to the ArrayList tasks.
 */
public class DeadlineCommand extends Command {
    String taskName;
    String arguments;

    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes by creating a Deadline task from the task description and deadline.
     * Adds the created Deadline task into ArrayList tasks and prints the add tasks message.
     *
     * @param tasks ArrayList of Task objects.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int DEADLINE_OFFSET_IDX = 4;
        int taskDescriptionEndIndex = arguments.indexOf("/") - 1;
        int deadlineIndex = arguments.indexOf("/by") + DEADLINE_OFFSET_IDX;
        taskName = arguments.substring(0, taskDescriptionEndIndex);
        String deadline = arguments.substring(deadlineIndex);
        Task task = new Deadline(taskName, deadline);
        tasks.tasks.add(task);
        ui.printAddTaskMsg(task.toString(), tasks.tasks.size());
        Storage.saveData(tasks.tasks);
    }
}
