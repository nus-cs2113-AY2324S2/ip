package alpaca.commands;

import alpaca.tasks.TaskList;
import alpaca.tasks.Task;
import alpaca.ui.Ui;

/**
 * Represents a command to add a new task to the task list
 */
public class AddCommand extends AlpacaCommand{
    private Task newTask;

    /**
     * Constructs an {@code AddCommand} with the specified task.
     *
     * @param newTask The task to be added.
     * @param tasks The current task list.
     */
    public AddCommand(Task newTask, TaskList tasks) {
        super(tasks);
        this.newTask = newTask;
    }

    /**
     * Executes the command to add the new task to the task list and
     * displays a confirmation message.
     */
    @Override
    public void execute(){
        tasks.addTask(newTask);
        Ui.printAddTask(newTask, tasks.getTotalTaskNumber());
    }
}
