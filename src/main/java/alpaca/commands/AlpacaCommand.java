package alpaca.commands;

import alpaca.tasks.TaskList;
import alpaca.ui.Ui;

public abstract class AlpacaCommand {
    protected TaskList tasks;
    protected Ui ui;

    AlpacaCommand () {
    }

    AlpacaCommand (Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public abstract void execute();

}
