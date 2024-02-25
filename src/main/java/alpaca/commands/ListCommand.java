package alpaca.commands;

import alpaca.tasks.TaskList;
import alpaca.ui.Ui;

public class ListCommand extends AlpacaCommand{

    public ListCommand(Ui ui, TaskList tasks) {
        super(ui, tasks);
    }

    @Override
    public void execute(){
        ui.printTaskList(tasks);
    }

}
