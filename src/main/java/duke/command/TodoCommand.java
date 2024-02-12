package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;

public class TodoCommand implements Command {

    private final String INPUT;

    public TodoCommand(String input) {
        this.INPUT = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (INPUT.isEmpty()) {
            throw new DukeException("Exceed Charge....\n\t " +
                    "OOPS!!! The description of a todo task cannot be empty.\n\t " +
                    "todo: Adds a todo task to task list.\n\t " +
                    "Parameters: TASK\n\t " +
                    "Example: todo borrow book");
        } else {
            Task newTodo = new Todo(INPUT);
            storage.addTask(newTodo.toDisk());
            taskList.add(newTodo);
            String msg = (taskList.size() > 1) ? "tasks" : "task";
            ui.printMessage("Got it. I've added this task: \n\t   " + newTodo
                    + "\n\t Now you have " + taskList.size() + " " + msg + " in the list.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
