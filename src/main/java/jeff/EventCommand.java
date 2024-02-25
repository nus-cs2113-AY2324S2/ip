package jeff;

public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        Event event = new Event(description, from, to);
        TaskList.add(event);
        Storage.appendTask(event);
        Printer.printAddTask();
    }
}
