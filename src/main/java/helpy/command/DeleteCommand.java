package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Task;
import helpy.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command{
    public DeleteCommand(String body) {
        super(body);
    }

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
