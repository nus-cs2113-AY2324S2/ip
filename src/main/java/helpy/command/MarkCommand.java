package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Task;
import helpy.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to mark a task as done or unmark a task.
 */
public class MarkCommand extends Command{
    private String commandStart = "";

    /**
     * Constructs a new MarkCommand object with the given command start and body.
     *
     * @param start The start of the command, either "mark" or "unmark".
     * @param body  The body or content of the command.
     */
    public MarkCommand(String start, String body) {
        super(body);
        commandStart = start;
    }

    /**
     * Executes the MarkCommand by marking the specified task as done or unmarking the task.
     *
     * @param taskList The task list containing the task to be marked or unmarked.
     * @param ui       The user interface for displaying output and messages.
     * @param storage  The storage system for managing task data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int taskIndex = Integer.parseInt(commandBody) - 1;
            boolean wasExecuted = taskList.markTask(commandStart, taskIndex, ui);
            if (!wasExecuted) {
                return;
            }
            ui.showMarkMessage(taskList.getTask(taskIndex));
            storage.update(taskList);
        } catch (NumberFormatException e) {
            ui.showInvalidTaskNumErr();
        } catch (IndexOutOfBoundsException e) {
            ui.showAbsentTaskNumErr();
        } catch (IOException e) {
            ui.showIOExceptionErr();
        }
    }
}
