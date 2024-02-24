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
        this.INPUT = input.trim();
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
        Task newEvent = getNewEvent();
        storage.addTask(newEvent.toDisk());
        taskList.add(newEvent);
        String msg = (taskList.size() > 1) ? "tasks" : "task";
        ui.printMessage("Got it. I've added this task:\n\t   " + newEvent
                + "\n\t Now you have " + taskList.size() + " " + msg + " in the list.");
    }

    /**
     * Converts the input string into an event task.
     *
     * @return A new event task
     * @throws DukeException If there is an error in user input.
     */
    private Task getNewEvent() throws DukeException {
        final String EXCEPTION = "Exceed Charge....\n\t " +
                "OOPS!!! The description of an event task cannot be empty.\n\t " +
                "event: Adds an event task to task list.\n\t " +
                "Parameters: TASK /from START TIME /to END TIME (both in date: yyyy-mm-dd time: HHmm format)\n\t " +
                "Example: event meet friends /from 2024-05-01 0610 /to 2024-06-01 1720";
        String[] details = INPUT.split("/from");
        boolean isEmptyInput = INPUT.isEmpty();
        boolean isWrongDeadline = details.length != 2;
        boolean isValidInput = isEmptyInput || isWrongDeadline;
        if (isValidInput) {
            throw new DukeException(EXCEPTION);
        }
        boolean isDetailsEmpty = details[1].trim().isEmpty();
        if (isDetailsEmpty) {
            throw new DukeException(EXCEPTION);
        }
        String[] times = details[1].split("/to");
        boolean isTimesEmpty = times.length != 2;
        boolean isFromEmpty = times[0].trim().isEmpty();
        boolean isToEmpty = times[1].trim().isEmpty();
        boolean isValidTimes = isFromEmpty || isToEmpty;
        if (isTimesEmpty || isValidTimes) {
            throw new DukeException(EXCEPTION);
        }
        String description = details[0].trim();
        String from = times[0].trim();
        String to = times[1].trim();
        return new Event(description, from, to);
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
