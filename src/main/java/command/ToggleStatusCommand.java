package command;

import exception.AdamException;
import task.Task;
import task.TaskList;
import ui.Message;

/**
 * The ToggleStatusCommand class represents a command to toggle the status of a task.
 */
public class ToggleStatusCommand implements Command {
    private final int index;
    private final String command;

    /**
     * Constructs a ToggleStatusCommand object.
     *
     * @param inputArguments It contains the command and the index of the task to be toggled,
     * at index 0 and 1 respectively.
     */
    public ToggleStatusCommand(String[] inputArguments) {
        this.command = inputArguments[0];
        this.index = Integer.parseInt(inputArguments[1]);
    }

    /**
     * Toggles the status of the task at the specified index.
     *
     * @param tasks The list of tasks.
     * @return False because the program should continue running.
     * @throws AdamException If the index is out of bounds.
     */
    @Override
    public boolean execute(TaskList tasks) throws AdamException {
        try {
            tasks.getTask(index).setIsDone(command.equals("mark"));
            System.out.println(toggleMessage(tasks.getTask(index)));
        } catch (IndexOutOfBoundsException error) {
            throw new AdamException(Message.getListInquiryErrorMessage(tasks.size()));
        }

        return false;
    }

    /**
     * Returns the message to be displayed after toggling the status of the task.
     *
     * @param task The task whose status was toggled.
     * @return The message to be displayed after toggling the status of the task.
     */
    private String toggleMessage(Task task) {
        return (task.isDone() ? Message.MARK_MESSAGE : Message.UNMARK_MESSAGE)
                + "   " + task;
    }
}
