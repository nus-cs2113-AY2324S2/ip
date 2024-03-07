package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Deadline;
import helpy.task.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to create a new Deadline task.
 */
public class DeadlineCommand extends Command{
    /**
     * Constructs a new DeadlineCommand object with the given command body.
     *
     * @param body The body or content of the command.
     */
    public DeadlineCommand(String body) {
        super(body);
    }

    /**
     * Executes the DeadlineCommand by creating a new Deadline task and adding it to the task list.
     *
     * @param taskList The task list to which the new Deadline task should be added.
     * @param ui       The user interface for displaying output and messages.
     * @param storage  The storage system for managing task data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Deadline newDeadline = new Deadline(commandBody);
            taskList.addTask(newDeadline);
            ui.showAddMessage(newDeadline, taskList.getListLength());
            storage.update(taskList);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            ui.showDeadlineDescErr();
        } catch (IOException e) {
            ui.showIOExceptionErr();
        }
    }
}
