package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Task;
import helpy.task.TaskList;

import java.io.IOException;

public class MarkCommand extends Command{
    private String commandStart = "";
    public MarkCommand(String start, String body) {
        super(body);
        commandStart = start;
    }

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
