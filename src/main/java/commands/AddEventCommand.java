package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Event;

/**
 * A command to add a new Event task to the task list.
 * Encapsulates the information need to create a new Event task,
 * including the description and the time period for which the event will take place.
 */
public class AddEventCommand extends Command {
    private String description;
    private String start;
    private String end;

    public AddEventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the command by creating a new Event task,
     * adding it to the task list and displaying a confirmation message to the user.
     *
     * @param tasks The TaskList currently managed by KikuBot, which can be modified by the command.
     * @param ui The Ui component of KikuBot used for interacting with the user.
     * @param storage The Storage component of KikuBot, responsible for tracking task list changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, start, end);
        tasks.addTask(event);
        ui.showTask("Got it. I've added this task: \n" + event);
        ui.showTask("Now you have " + tasks.getTasks().size() + " tasks in the list!");
    }
}
