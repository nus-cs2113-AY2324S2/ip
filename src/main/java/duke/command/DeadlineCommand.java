package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a command for the Deadline task.
 */
public class DeadlineCommand implements Command {

    private final String INPUT; //deadline return book /by saturday

    /**
     * Constructs a new DeadlineCommand object with user input.
     *
     * @param input User input of the deadline command.
     */
    public DeadlineCommand(String input) {
        this.INPUT = input.trim();
    }

    /**
     * Executes the command by creating a new Deadline object and adding the Deadline task to the task list.
     * Displays the added task to the screen.
     *
     * @param taskList The lists of tasks of the Duke chatbot.
     * @param ui The user interface of the Duke chatbot.
     * @param storage The file storage of the Duke chatbot.
     * @throws DukeException If there is an error in the user's input.
     * @throws IOException If there is an error appending the new task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String[] details = INPUT.split("/by");
        if (INPUT.isEmpty() || details.length != 2 || details[0].trim().isEmpty() || details[1].trim().isEmpty()) {
            throw new DukeException("Exceed Charge....\n\t " +
                    "OOPS!!! The description of a deadline task cannot be empty.\n\t " +
                    "deadline: Adds a deadline task to task list.\n\t " +
                    "Parameters: TASK /by DEADLINE (in date: yyyy-mm-dd time: HHmm format)\n\t " +
                    "Example: deadline return book /by 2024-05-01 1800");
        } else {
            String description = details[0].trim();
            String by = details[1].trim();
            Task newDeadline = new Deadline(description, by);
            storage.addTask(newDeadline.toDisk());
            taskList.add(newDeadline);
            String msg = (taskList.size() > 1) ? "tasks" : "task";
            ui.printMessage("Got it. I've added this task:\n\t   " + newDeadline
                    + "\n\t Now you have " + taskList.size() + " " + msg + " in the list.");
        }
    }

    /**
     * Indicates whether this is an exit command.
     * Returns false since this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
