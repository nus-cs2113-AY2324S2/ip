package jeff.commands;

import jeff.*;
import jeff.tasks.Event;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an EventCommand object with the given description, start time, and end time.
     *
     * @param description Description of the event task.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command by creating a new event task, adding it to the task list,
     * appending it to storage, and printing a message indicating the task has been added.
     */
    @Override
    public void execute() {
        Event event = new Event(description, from, to);
        TaskList.add(event);
        Storage.appendTask(event);
        Printer.printAddTask();
    }
}
