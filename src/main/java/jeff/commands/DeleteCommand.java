package jeff.commands;

import jeff.*;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;
    private final Task task;

    /**
     * Constructs a DeleteCommand object with the given index and task.
     *
     * @param index Index of the task to be deleted.
     * @param task Task to be deleted.
     */
    public DeleteCommand(int index, Task task) {
        this.index = index;
        this.task = task;
    }

    /**
     * Executes the delete command by removing the task from the task list,
     * deleting it from storage, and printing a message indicating the task has been deleted.
     */
    @Override
    public void execute() {
        TaskList.remove(index);
        Storage.deleteTask(index);
        Printer.printDeleteTask(task);
    }
}
