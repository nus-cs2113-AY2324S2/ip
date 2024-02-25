package command;

import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.Task;
import tasktype.TaskList;
import tasktype.Todo;
import ui.Ui;

/**
 * Represents the command to add a todo task.
 */
public class TodoCommand implements Command {
    private final String userInput;

    /**
     * Constructs a TodoCommand with the user's input.
     * @param description The description of the task from the user.
     */
    public TodoCommand(String description) {
        userInput = description;
    }

    /**
     * Executes the command by creating a todo object and adding the todo task to the existing task list.
     * Displays todo task to be added and the corresponding status on the screen after executing the command.
     *
     * @param taskList The list of task in the JingHao chatbot.
     * @param ui The user interface of the JingHao chatbot.
     * @param storage The file storage of the JingHao chatbot.
     * @throws JingHaoExceptions If the description of the task is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions {
        if(userInput.isBlank()) {
            String invalidTodoInput = "Description of Todo cannot be empty.";
            throw new JingHaoExceptions(invalidTodoInput);
        }
        Task newTodo = new Todo(userInput);
        taskList.add(newTodo);
        System.out.println("Got it. I've added this task:\n " + newTodo);
        ui.printTotalTask(taskList.size());
    }

    /**
     * Determines whether the command is an exit command.
     *
     * @return Returns false since this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
