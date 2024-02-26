package jeff.commands;

import jeff.Command;
import jeff.Printer;
import jeff.Storage;
import jeff.Task;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private final int index;
    private final Task task;

    /**
     * Constructs a UnmarkCommand object with the given index and task.
     *
     * @param index Index of the task to be unmarked.
     * @param task Task to be unmarked.
     */
    public UnmarkCommand(int index, Task task) {
        this.index = index;
        this.task = task;
    }

    /**
     * Executes the unmark command by unmarking the task, updating its status in storage,
     * and printing a message indicating the task has been unmarked.
     */
    @Override
    public void execute() {
        task.unmark();
        Storage.updateMarkStatus(index, false);
        Printer.printUnmarkTask(task);
    }
}
