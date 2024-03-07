package alpaca.commands;

import alpaca.tasks.TaskList;

/**
 * Parent class for all commands
 */
public abstract class AlpacaCommand {
    protected TaskList tasks;

    public AlpacaCommand () {
    }

    public AlpacaCommand (TaskList tasks) {
        this.tasks = tasks;
    }

    public abstract void execute();

}
