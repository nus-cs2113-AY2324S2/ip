package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Deadline;

/**
 * A command to add a Deadline task to the task list.
 * Encapsulates the information need to create a new Deadline task,
 * including the description and deadline to be completed by when.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command by creating a new Deadline task,
     * adding it to the task list and displaying a confirmation message to the user.
     *
     * @param tasks The TaskList currently managed by KikuBot, which can be modified by the command.
     * @param ui The Ui component of KikuBot used for interacting with the user.
     * @param storage The Storage component of KikuBot, responsible for tracking task list changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.showTask("Alright, I've added this task: \n" + deadline);
        ui.showTask("Now you have " + tasks.getTasks().size() + " tasks in the list!");
    }
}
