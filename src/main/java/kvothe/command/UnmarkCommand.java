package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;
import kvothe.exception.WrongArgumentsException;
import kvothe.task.Task;

public class UnmarkCommand extends Command{
    public UnmarkCommand(String[] args){
        super(args);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        String index = this.args[0];
        try {
            tasks.setStatus(index, false);
            ui.echo("OK, I've marked this task as not done yet:\n\t\t" + tasks.get(index));
            storage.dumpToFile(tasks.getTasks());
        } catch (WrongArgumentsException e) {
            ui.echo(e.getMessage());
        }
    }

}
