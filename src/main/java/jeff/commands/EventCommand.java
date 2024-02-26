package jeff.commands;

import jeff.Command;
import jeff.Printer;
import jeff.Storage;
import jeff.TaskList;
import jeff.tasks.Event;

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
