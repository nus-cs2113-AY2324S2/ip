package jeff.commands;

import jeff.Command;
import jeff.Printer;
import jeff.Storage;
import jeff.TaskList;
import jeff.tasks.Deadline;
import java.time.LocalDate;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    /**
     * Constructs a DeadlineCommand object with the given description and deadline date.
     *
     * @param description Description of the deadline task.
     * @param by Deadline date of the deadline task.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the deadline command by creating a new deadline task, adding it to the task list,
     * appending it to storage, and printing a message indicating the task has been added.
     */
    @Override
    public void execute() {
        Deadline deadline = new Deadline(description, by);
        TaskList.add(deadline);
        Storage.appendTask(deadline);
        Printer.printAddTask();
    }
}
