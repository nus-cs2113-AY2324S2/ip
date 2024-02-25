package quill.command;

import quill.task.TaskList;
import quill.ui.TextUi;

/**
 * The DeleteCommand Class represents a command for deleting a task.
 * It extends the Command class and includes specific behavior for deleting a task.
 */
public class DeleteCommand extends Command{

    /**
     * Constructs the DeleteCommand object with the specified commandWord and parameter.
     *
     * @param commandWord The command word.
     * @param parameter The user input excluding the command word.
     */
    public DeleteCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    /**
     * Executes the DeleteCommand to delete the task from task list.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui) {
        try {
            int taskNumber = Integer.parseInt(parameter) - 1;
            TextUi.showDeleteTask(tasks.getTask(taskNumber));
            tasks.removeTask(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Hey, wake up! That task? Non-existent. Try something real.");
        } catch (NumberFormatException e) {
            System.out.println("Listen up! Numbers only, got it? Don't bother with anything else");
        }
    }
}
