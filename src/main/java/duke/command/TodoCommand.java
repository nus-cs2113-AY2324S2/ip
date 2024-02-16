package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a command for the Todo task.
 */
public class TodoCommand implements Command {

    private final String INPUT;

    /**
     * Constructs a new TodoCommand object with user input.
     * @param input User input of the todo command.
     */
    public TodoCommand(String input) {
        this.INPUT = input;
    }

    /**
     * Executes the command by creating a new Todo object and adding the Todo task to the task list.
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
