package alpaca.commands;

import alpaca.exceptions.InvalidIndexException;
import alpaca.tasks.TaskList;
import alpaca.ui.Ui;

/**
 * Represents a command to mark or unmark a task from the task list
 */
public class MarkUnmarkCommand extends AlpacaCommand{
    private int index;
    private boolean isMark;

    /**
     * Constructs a {@code MarkUnmarkCommand} for marking or unmarking a task.
     *
     * @param index The index of the task in the task list to be marked or unmarked.
     * @param isMark {@code true} to mark the task as completed, {@code false} to unmark.
     * @param tasks The current task list.
     */
    public MarkUnmarkCommand(int index, boolean isMark, TaskList tasks) {
        super(tasks);
        this.index = index;
        this.isMark = isMark;
    }

    /**
     * Mark or unmark the task from the task list
     */
    @Override
    public void execute() {
        try {
            if (!tasks.isCountValid(index)) {
                throw new InvalidIndexException();
            }
            if (isMark) {
                tasks.markTask(index);
            } else {
                tasks.unmarkTask(index);
            }
            Ui.printLine();
        } catch (InvalidIndexException e) {
            Ui.printErrorMessage(e.toString());
        }
    }
}