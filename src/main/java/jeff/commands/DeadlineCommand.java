package jeff.commands;

import jeff.*;
import jeff.tasks.Deadline;

public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute() {
        Deadline deadline = new Deadline(description, by);
        TaskList.add(deadline);
        Storage.appendTask(deadline);
        Printer.printAddTask();
    }
}
