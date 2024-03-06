package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.exceptions.IllegalDescriptionException;
import helpy.task.Task;
import helpy.task.TaskList;
import helpy.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a command to create a new Todo task.
 */
public class TodoCommand extends Command{
    /**
     * Constructs a new TodoCommand object with the given command body.
     *
     * @param body The body or content of the command.
     */
    public TodoCommand(String body) {
        super(body);
    }

    /**
     * Executes the TodoCommand by creating a new Todo task and adding it to the task list.
     *
     * @param taskList The task list to which the new Todo task should be added.
     * @param ui       The user interface for displaying output and messages.
     * @param storage  The storage system for managing task data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Todo newTodo = new Todo(commandBody);
            taskList.addTask(newTodo);
            ui.showAddMessage(newTodo, taskList.getListLength());
            storage.update(taskList);
        } catch (IllegalDescriptionException e) {
            ui.showIllegalDescriptionErr();
        } catch (IOException e) {
            ui.showIOExceptionErr();
        }
    }
}
