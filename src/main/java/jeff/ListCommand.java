package jeff;

public class ListCommand extends Command {
    @Override
    public void execute() {
        Printer.printTasks();
    }
}
