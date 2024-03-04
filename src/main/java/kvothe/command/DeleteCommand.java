package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;
import kvothe.exception.KvotheException;
import kvothe.exception.WrongArgumentsException;
import kvothe.task.Task;


public class DeleteCommand extends Command{

    public DeleteCommand(String[] args){
        super(args);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        String index = this.args[0];
        Task task = null;

        try{
            task = tasks.remove(index);
            ui.showDelete(task, tasks.size());
            storage.dumpToFile(tasks.getTasks());
        } catch (WrongArgumentsException e){
            ui.echo(e.getMessage());
        }


    }
}
