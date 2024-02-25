package jeff;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute() {
        Todo todo = new Todo(description);
        TaskList.add(todo);
        Storage.appendTask(todo);
        Printer.printAddTask();
    }
}
