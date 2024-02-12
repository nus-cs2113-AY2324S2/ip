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
    private String description;
    private String from;
    private String to;

    public EventCommand(String input) {
        this.INPUT = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (INPUT.isEmpty()) {
            throw new DukeException("Exceed Charge....\n\t " +
                    "OOPS!!! The description of an event task cannot be empty.\n\t " +
                    "event: Adds an event task to task list.\n\t " +
                    "Parameters: TASK /from START TIME /to END TIME\n\t " +
                    "Example: event meet friends /from 9pm today /to 11pm today");
        } else {
            splitWords();
            Task newEvent = new Event(description, from, to);
            storage.addTask(newEvent.toDisk());
            taskList.add(newEvent);
            String msg = (taskList.size() > 1) ? "tasks" : "task";
            ui.printMessage("Got it. I've added this task: \n\t   " + newEvent
                    + "\n\t Now you have " + taskList.size() + " " + msg + " in the list.");
        }
    }

    @Override
    public void splitWords() {
        String[] words = INPUT.split("/");
        final int FROM_LENGTH = 5;
        final int TO_LENGTH = 3;
        description = words[0];
        from = words[1].substring(FROM_LENGTH);
        to = words[2].substring(TO_LENGTH);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
