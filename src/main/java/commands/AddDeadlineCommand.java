package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Deadline;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.showTask("Got it. I've added this task: \n" + deadline);
        ui.showTask("Now you have " + tasks.getTasks().size() + " tasks in the list~");
    }
}
