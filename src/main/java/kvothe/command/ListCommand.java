package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;

/**
 * ListCommand class
 * This class is a subclass of Command
 */
public class ListCommand extends Command{

    public ListCommand(){
    }

    /**
     * This method prints out the list of tasks
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        for (int i = 0; i < tasks.size(); i++) {
            ui.echo(i + 1 + ". " + tasks.get(i), i == 0, i == tasks.size() - 1);
        }
    }
}
