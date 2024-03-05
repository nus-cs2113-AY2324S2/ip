package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.exceptions.IllegalDescriptionException;
import helpy.task.Task;
import helpy.task.TaskList;
import helpy.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TodoCommand extends Command{
    public TodoCommand(String body) {
        super(body);
    }

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
