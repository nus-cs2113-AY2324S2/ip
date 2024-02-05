package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.ui.Ui;

public class DeadlineCommand implements Command {

    private final String INPUT; //deadline return book /by saturday

    public DeadlineCommand(String input) {
        this.INPUT = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (INPUT.isEmpty()) {
            throw new DukeException("Exceed Charge....\n\t " +
                    "OOPS!!! The description of a deadline task cannot be empty.\n\t " +
                    "deadline: Adds a deadline task to task list.\n\t " +
                    "Parameters: TASK /by DEADLINE\n\t " +
                    "Example: deadline return book /by saturday");
        } else {
            String[] words = INPUT.split("/by ");
            Task newDeadline = new Deadline(words[0], words[1]);
            taskList.add(newDeadline);
            String msg = (taskList.size() > 1) ? "tasks" : "task";
            ui.printMessage("Got it. I've added this task: \n\t   " + newDeadline
                    + "\n\t Now you have " + taskList.size() + " " + msg + " in the list.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
