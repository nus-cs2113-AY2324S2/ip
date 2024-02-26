package jeff.commands;

import jeff.Command;
import jeff.Printer;
import jeff.Storage;
import jeff.Task;

/**
 * Represents a command to mark a task.
 */
public class MarkCommand extends Command {
    private final int index;
    private final Task task;

    /**
     * Constructs a MarkCommand object with the given index and task.
     *
     * @param index Index of the task to be marked.
     * @param task Task to be marked.
     */
    public MarkCommand(int index, Task task) {
        this.index = index;
        this.task = task;
    }

    /**
     * Executes the mark command by marking the task, updating its status in storage,
     * and printing a message indicating the task has been marked.
     */
    @Override
    public void execute() {
        task.mark();
        Storage.updateMarkStatus(index, true);
        Printer.printMarkTask(task);
    }
}
