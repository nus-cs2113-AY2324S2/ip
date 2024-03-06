package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Event;
import helpy.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to create a new Event task.
 */
public class EventCommand extends Command{
    /**
     * Constructs a new EventCommand object with the given command body.
     *
     * @param body The body or content of the command.
     */
    public EventCommand(String body) {
        super(body);
    }

    /**
     * Executes the EventCommand by creating a new Event task and adding it to the task list.
     *
     * @param taskList The task list to which the new Event task should be added.
     * @param ui       The user interface for displaying output and messages.
     * @param storage  The storage system for managing task data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Event newEvent = new Event(commandBody);
            taskList.addTask(newEvent);
            ui.showAddMessage(newEvent, taskList.getListLength());
            storage.update(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showEventDescErr();
        } catch (IOException e) {
            ui.showIOExceptionErr();
        }
    }
}
