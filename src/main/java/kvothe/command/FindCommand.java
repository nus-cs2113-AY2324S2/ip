package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;

public class FindCommand extends Command{
    public FindCommand(String[] args){
        super(args);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        String keyword = this.args[0];

        ui.echo("Here are the matching tasks in your list:", true, false);

        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getDescription().contains(keyword)){
                ui.echo(i + 1 + ". " + tasks.get(i), false, false);
            }
        }

        ui.echo("", false, true);
    }
}
