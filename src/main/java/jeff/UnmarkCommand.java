package jeff;

public class UnmarkCommand extends Command {
    private final int index;
    private final Task task;

    public UnmarkCommand(int index, Task task) {
        this.index = index;
        this.task = task;
    }

    @Override
    public void execute() {
        task.unmark();
        Storage.updateMarkStatus(index, false);
        Printer.printUnmarkTask(task);
    }
}
