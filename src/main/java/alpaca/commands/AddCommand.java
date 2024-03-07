package alpaca.commands;

import alpaca.tasks.TaskList;
import alpaca.tasks.Task;
import alpaca.ui.Ui;


public class AddCommand extends AlpacaCommand{
    private Task newTask;
    public AddCommand(Task newTask, TaskList tasks) {
        super(tasks);
        this.newTask = newTask;
    }

    @Override
    public void execute(){
        tasks.addTask(newTask);
        Ui.printAddTask(newTask, tasks.getTotalTaskNumber());
    }
}
