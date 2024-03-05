package bossman.command;

import bossman.ui.Ui;
import bossman.exceptions.commandexceptions.InvalidTodoCommandException;
import bossman.task.Task;
import bossman.task.TaskList;
import bossman.task.Todo;

/**
 * ToDoCommand represents the command to add a todo task.
 * It implements the Command interface.
 */
public class ToDoCommand implements Command{
    private TaskList userTasks;
    private String taskDescription;

    /**
     * Constructs a ToDoCommand with the provided TaskList and command arguments.
     *
     * @param userTasks the TaskList containing user's tasks
     * @param commandArgs the arguments provided with the command
     * @throws InvalidTodoCommandException if the command arguments are invalid
     */
    public ToDoCommand(TaskList userTasks, String commandArgs) throws InvalidTodoCommandException {
        if(commandArgs.isBlank()) {
            throw new InvalidTodoCommandException("Invalid todo command");
        }

        this.userTasks = userTasks;
        taskDescription = commandArgs;
    }

    /**
     * Executes the ToDoCommand by adding the todo task to the TaskList.
     * Prints a confirmation message with the added task and the updated task count.
     */
    @Override
    public void execute() {
        Task todoTask = new Todo(taskDescription, false);
        userTasks.addTask(todoTask);

        //print confirmation message
        Ui.printMessageNoSepSameLine("Added:");
        todoTask.printTask();
        Ui.printMessageNoSepNewLine("");
        Ui.printMessageWithSepNewLine("Now you have " + userTasks.getTaskListSize() + " tasks in the list.");
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, indicating that this command is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
