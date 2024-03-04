package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;

public class ListCommand extends Command{

    public ListCommand(){
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        for (int i = 0; i < tasks.size(); i++) {
            ui.echo(i + 1 + ". " + tasks.get(i), i == 0, i == tasks.size() - 1);
        }
    }
}
