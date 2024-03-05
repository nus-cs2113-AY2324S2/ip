package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command{
    public FindCommand(String[] args){
        super(args);
    }

    /**
     * Executes the command to find tasks in the task list.
     * It searches all tasks containing a keyword (stored in args[0])
     * and prints them out.
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage.
     */
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
