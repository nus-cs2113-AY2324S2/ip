package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.ui.Ui;

import java.io.IOException;

public class DeadlineCommand implements Command {

    private final String INPUT; //deadline return book /by saturday
    private String description;
    private String by;

    public DeadlineCommand(String input) {
        this.INPUT = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (INPUT.isEmpty()) {
            throw new DukeException("Exceed Charge....\n\t " +
                    "OOPS!!! The description of a deadline task cannot be empty.\n\t " +
                    "deadline: Adds a deadline task to task list.\n\t " +
                    "Parameters: TASK /by DEADLINE\n\t " +
                    "Example: deadline return book /by saturday");
        } else {
            splitWords();
            Task newDeadline = new Deadline(description, by);
            storage.addTask(newDeadline.toDisk());
            taskList.add(newDeadline);
            String msg = (taskList.size() > 1) ? "tasks" : "task";
            ui.printMessage("Got it. I've added this task: \n\t   " + newDeadline
                    + "\n\t Now you have " + taskList.size() + " " + msg + " in the list.");
        }
    }

    @Override
    public void splitWords() {
        String[] words = INPUT.split("/by ");
        description = words[0];
        by = words[1];
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
