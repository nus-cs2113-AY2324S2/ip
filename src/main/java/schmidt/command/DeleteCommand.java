package schmidt.command;

import schmidt.exception.SchmidtException;
import schmidt.storage.Storage;
import schmidt.task.Task;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SchmidtException {
        Task deletedTask;
        try {
            deletedTask = tasks.deleteTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new SchmidtException("Invalid task number");
        }

        try {
            storage.save(tasks);
        } catch (Exception e) {
            throw new SchmidtException("Sorry, there was an error saving the task to storage.");
        }

        ui.printTaskDeleted(deletedTask, tasks.getSize());
    }
}
