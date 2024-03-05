package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Deadline;
import helpy.task.TaskList;

import java.io.IOException;

public class DeadlineCommand extends Command{
    public DeadlineCommand(String body) {
        super(body);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Deadline newDeadline = new Deadline(commandBody);
            taskList.addTask(newDeadline);
            ui.showAddMessage(newDeadline, taskList.getListLength());
            storage.update(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showDeadlineDescErr();
        } catch (IOException e) {
            ui.showIOExceptionErr();
        }
    }
}
