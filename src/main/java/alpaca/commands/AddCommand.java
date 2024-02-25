package alpaca.commands;

import alpaca.tasks.TaskList;
import alpaca.tasks.Task;
import alpaca.ui.Ui;


public class AddCommand extends AlpacaCommand{
    private Task newTask;
    public AddCommand(Ui ui,Task newTask, TaskList tasks) {
        super(ui, tasks);
        this.newTask = newTask;
    }

    @Override
    public void execute(){
        tasks.addTask(newTask);
        ui.printAddTask(newTask, tasks.getTotalTaskNumber());
    }
}
