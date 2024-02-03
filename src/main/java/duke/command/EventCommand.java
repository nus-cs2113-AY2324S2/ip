package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand implements Command {

    private final String INPUT; // event meet friends /from 5pm tdy /to 9pm tdy

    public EventCommand(String input) {
        this.INPUT = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (INPUT.isEmpty()) {
            throw new DukeException("Exceed Charge.... \n\t " +
                    "OOPS!!! The description of an event task cannot be empty.");
        } else {
            String[] words = INPUT.split("/");
            final int FROM_LENGTH = 5;
            final int TO_LENGTH = 3;
            Task newEvent = new Event(words[0], words[1].substring(FROM_LENGTH), words[2].substring(TO_LENGTH));
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
