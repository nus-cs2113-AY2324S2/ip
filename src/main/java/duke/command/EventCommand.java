package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a command for the Event task.
 */
public class EventCommand implements Command {

    private final String INPUT; // event meet friends /from 5pm tdy /to 9pm tdy

    /**
     * Constructs a new EventCommand object with user input.
     *
     * @param input User input of the event command.
     */
    public EventCommand(String input) {
        this.INPUT = input;
    }

    /**
     * Executes the command by creating a new Event object and adding the Event task to the task list.
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
        final String EXCEPTION = "Exceed Charge....\n\t " +
                "OOPS!!! The description of an event task cannot be empty.\n\t " +
                "event: Adds an event task to task list.\n\t " +
                "Parameters: TASK /from START TIME /to END TIME\n\t " +
                "Example: event meet friends /from 9pm today /to 11pm today";

        String[] details = INPUT.split("/from ");
        if (INPUT.isEmpty() || details.length != 2 || details[1].isEmpty()) {
            throw new DukeException(EXCEPTION);
        } else {
            String[] times = details[1].split("/to ");
            if (times.length != 2 || times[1].isEmpty()) {
                throw new DukeException(EXCEPTION);
            }
            Task newEvent = new Event(details[0], times[0], times[1]);
            storage.addTask(newEvent.toDisk());
            taskList.add(newEvent);
            String msg = (taskList.size() > 1) ? "tasks" : "task";
            ui.printMessage("Got it. I've added this task: \n\t   " + newEvent
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
