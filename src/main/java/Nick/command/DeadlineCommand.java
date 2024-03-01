package Nick.command;

import Nick.storage.Storage;
import Nick.task.TaskList;
import Nick.ui.Ui;
import Nick.task.Deadline;
import Nick.task.Task;

public class DeadlineCommand extends Command {
    String taskName;
    String arguments;

    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

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
