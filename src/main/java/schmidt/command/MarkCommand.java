package schmidt.command;

import schmidt.exception.SchmidtException;
import schmidt.storage.Storage;
import schmidt.task.Task;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

public class MarkCommand extends Command {
    private final int index;
    private final boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SchmidtException {
        Task markedTask;
        try {
            markedTask = tasks.setDone(index, isDone);
        } catch (Exception e) {
            throw new SchmidtException("Please enter a valid task number\n\t[un]mark <task number>");
        }

        try {
            storage.save(tasks);
        } catch (Exception e) {
            throw new SchmidtException("Sorry, there was an error saving the task to storage.");
        }

        if (isDone) {
            ui.printTaskDone(markedTask);
        } else {
            ui.printTaskUndone(markedTask);
        }
    }
}
