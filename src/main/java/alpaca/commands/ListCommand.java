package alpaca.commands;

import alpaca.tasks.TaskList;
import alpaca.ui.Ui;

public class ListCommand extends AlpacaCommand{

    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public void execute(){
        Ui.printTaskList(tasks);
    }

}
