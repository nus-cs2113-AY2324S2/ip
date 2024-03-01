package Nick.command;

import Nick.Storage;
import Nick.TaskList;
import Nick.Ui;
import Nick.task.Deadline;
import Nick.task.Event;
import Nick.task.Task;

public class EventCommand extends Command {
    String taskName;
    String arguments;

    public EventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");
        int taskDescriptionEndIndex = arguments.indexOf("/") - 1;
        taskName = arguments.substring(0, taskDescriptionEndIndex);

        final int TO_OFFSET_IDX = 4;
        final int FROM_OFFSET_IDX = 6;
        String from = arguments.substring(fromIndex + FROM_OFFSET_IDX, toIndex - 1);
        String to = arguments.substring(toIndex + TO_OFFSET_IDX);
        Task task = new Event(taskName, from, to);
        tasks.tasks.add(task);
        ui.printAddTaskMsg(task.toString(), tasks.tasks.size());
        Storage.saveData(tasks.tasks);
    }
}
