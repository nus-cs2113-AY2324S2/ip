package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Event;

public class AddEventCommand extends Command {
    private String description;
    private String start;
    private String end;

    public AddEventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, start, end);
        tasks.addTask(event);
        ui.showTask("Got it. I've added this task: \n" + event);
        ui.showTask("Now you have " + tasks.getTasks().size() + " tasks in the list~");
    }
}
