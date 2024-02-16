package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class EventCommand implements Command {

    private final String INPUT; // event meet friends /from 5pm tdy /to 9pm tdy

    public EventCommand(String input) {
        this.INPUT = input.trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        final String EXCEPTION = "Exceed Charge....\n\t " +
                "OOPS!!! The description of an event task cannot be empty.\n\t " +
                "event: Adds an event task to task list.\n\t " +
                "Parameters: TASK /from START TIME /to END TIME (both in date: yyyy-mm-dd time: HHmm format)\n\t " +
                "Example: event meet friends /from 2024-05-01 0610 /to 2024-06-01 1720";

        String[] details = INPUT.split("/from");
        if (INPUT.isEmpty() || details.length != 2 || details[1].trim().isEmpty()) {
            throw new DukeException(EXCEPTION);
        } else {
            String[] times = details[1].split("/to");
            if (times.length != 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                throw new DukeException(EXCEPTION);
            }
            String description = details[0].trim();
            String from = times[0].trim();
            String to = times[1].trim();
            Task newEvent = new Event(description, from, to);
            storage.addTask(newEvent.toDisk());
            taskList.add(newEvent);
            String msg = (taskList.size() > 1) ? "tasks" : "task";
            ui.printMessage("Got it. I've added this task: \n\t   " + newEvent
                    + "\n\t Now you have " + taskList.size() + " " + msg + " in the list.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
