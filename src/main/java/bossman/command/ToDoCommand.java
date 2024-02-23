package bossman.command;

import bossman.ui.Ui;
import bossman.exceptions.BossManExceptions;
import bossman.exceptions.commandexceptions.InvalidTodoCommandException;
import bossman.task.Task;
import bossman.task.TaskList;
import bossman.task.Todo;

import java.io.IOException;

public class ToDoCommand implements Command{
    private TaskList userTasks;
    private String taskDescription;

    public ToDoCommand(TaskList userTasks, String commandArgs) throws InvalidTodoCommandException {
        if(commandArgs.isBlank()) {
            throw new InvalidTodoCommandException("Invalid todo command");
        }

        this.userTasks = userTasks;
        taskDescription = commandArgs;
    }

    @Override
    public void execute() {
        Task todoTask = new Todo(taskDescription, false);
        userTasks.addTask(todoTask);

        Ui.printMessageNoSepSameLine("Added:");
        todoTask.printTask();
        Ui.printMessageNoSepNewLine("");
        Ui.printMessageWithSepNewLine("Now you have " + userTasks.getTaskListSize() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
