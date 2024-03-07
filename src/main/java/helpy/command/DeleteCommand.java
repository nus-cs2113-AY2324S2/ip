package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Task;
import helpy.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command{
    /**
     * Constructs a new DeleteCommand object with the given command body.
     *
     * @param body The body or content of the command.
     */
    public DeleteCommand(String body) {
        super(body);
    }

    /**
     * Executes the DeleteCommand by removing the specified task from the task list.
     *
     * @param taskList The task list from which the task should be removed.
     * @param ui       The user interface for displaying output and messages.
     * @param storage  The storage system for managing task data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int taskIndex = Integer.parseInt(commandBody) - 1;
            Task removedTask = taskList.removeTask(taskIndex);
            int numOfTasks = taskList.getListLength();
            ui.showDeleteMessage(removedTask, numOfTasks);
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
