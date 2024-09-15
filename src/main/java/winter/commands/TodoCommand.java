package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;
import winter.task.Task;
import winter.task.Todo;

import java.io.IOException;


/**
 * Represents the command given by the user to add a Todo task
 */
public class TodoCommand extends Command {

    private String todo;
    public static final String COMMAND_WORD = "todo";

    private static final String MESSAGE_SUCCESS = "Great! New ToDo added: ";

    public TodoCommand(String todo) {
        this.todo = todo;
    }

    /**
     * Upon receiving the command from the user, create a new Todo object, add it to task list,
     * show the confirmation message and update it in storage
     * @param tasks The TaskList object representing a list of the tasks
     * @param ui The user interface that provides feedback to the user
     * @param storage The storage object which helps store changes made to the list
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task newTodo = new Todo(tasks.getCurrentTaskIndex(),false, todo);
        tasks.addNewTask(newTodo);
        //tasks.increaseLastTaskIndex();
        ui.showTaskAddedConfirm(tasks,newTodo);
        try {
            storage.writeToFile(formatTodoForStorage(newTodo), true);
        } catch (IOException e) {
            System.out.println("Could not write to file: " + e.getMessage());
        }
    }
    /**
     * Format the Todo object into a string that can be written to the storage file
     * @param newTodo The new deadline object that was created
     * @return String in the format that can be written to storage and read later
     */
    private String formatTodoForStorage(Task newTodo) {

        return "T" + " | " + newTodo.getIsMarked() + " | " +
                newTodo.getTaskName() + System.lineSeparator();
    }
}
