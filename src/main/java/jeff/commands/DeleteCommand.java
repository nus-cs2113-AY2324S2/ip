package jeff.commands;

import jeff.Command;
import jeff.Printer;
import jeff.Storage;
import jeff.Task;
import jeff.TaskList;

public class DeleteCommand extends Command {
    private final int index;
    private final Task task;

    public DeleteCommand(int index, Task task) {
        this.index = index;
        this.task = task;
    }

    @Override
    public void execute() {
        TaskList.remove(index);
        Storage.deleteTask(index);
        Printer.printDeleteTask(task);
    }
}
