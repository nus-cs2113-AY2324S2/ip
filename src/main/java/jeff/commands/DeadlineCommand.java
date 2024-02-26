package jeff.commands;

import jeff.*;
import jeff.tasks.Deadline;
import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
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
