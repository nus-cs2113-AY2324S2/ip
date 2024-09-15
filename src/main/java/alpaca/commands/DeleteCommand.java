package alpaca.commands;

import alpaca.exceptions.InvalidIndexException;
import alpaca.tasks.TaskList;
import alpaca.ui.Ui;

/**
 * Represents a command to delete a task from the task list
 */
public class DeleteCommand extends AlpacaCommand{
    private int index;

    /**
     * Constructs a {@code DeleteCommand} to remove a task at the specified index.
     *
     * @param index The index of the task in the task list to be deleted.
     * @param tasks The current task list.
     */
    public DeleteCommand (int index, TaskList tasks) {
        super(tasks);
        this.index = index;
    }

    /**
     * Executes the command to delete the task from the task list.
     * Validates the index and shows a message upon successful deletion or error.
     */
    @Override
    public void execute(){
        try {
            if (!tasks.isCountValid(index)) {
                throw new InvalidIndexException();
            }
            tasks.deleteTask(index);
            Ui.printLine();
        } catch (InvalidIndexException e) {
            Ui.printErrorMessage(e.toString());
        }
    }
}