package jeff.commands;

import jeff.Command;
import jeff.Printer;
import jeff.Storage;
import jeff.Task;

public class MarkCommand extends Command {
    private final int index;
    private final Task task;

    public MarkCommand(int index, Task task) {
        this.index = index;
        this.task = task;
    }

    @Override
    public void execute() {
        task.mark();
        Storage.updateMarkStatus(index, true);
        Printer.printMarkTask(task);
    }
}
