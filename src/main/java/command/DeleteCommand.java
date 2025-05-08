package command;

import exception.AdamException;
import task.TaskList;
import ui.Message;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param inputArguments The index of the task to be deleted.
     */
    public DeleteCommand(String[] inputArguments) {
        this.index = Integer.parseInt(inputArguments[0]);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param tasks The list of tasks.
     * @return False because the program should continue running.
     * @throws AdamException If the index is out of bounds.
     */
    @Override
    public boolean execute(TaskList tasks) throws AdamException {
        try {
            System.out.println(Message.getDeleteMessage(tasks.size() - 1, tasks.getTask(index).toString()));
            tasks.deleteTask(index);
        } catch (IndexOutOfBoundsException error) {
            throw new AdamException(Message.getListInquiryErrorMessage(tasks.size()));
        }
        return false;
    }
}
