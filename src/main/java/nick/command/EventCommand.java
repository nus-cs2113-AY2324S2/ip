package nick.command;

import nick.storage.Storage;
import nick.task.TaskList;
import nick.ui.Ui;
import nick.task.Event;
import nick.task.Task;

/**
 * Represents the Event command which inherits from the Command class.
 * It includes the execute method which adds an Event task to the ArrayList tasks.
 */
public class EventCommand extends Command {
    String taskName;
    String arguments;

    public EventCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes by creating an Event task from the task description, /from and /to arguments.
     * Adds the created Event task into ArrayList tasks and prints the add tasks message.
     *
     * @param tasks ArrayList of Task objects.
     * @param ui Ui object.
     * @param storage Storage object.
     */
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
