package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;
import kvothe.exception.KvotheException;
import kvothe.exception.WrongArgumentsException;
import kvothe.task.Task;


/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command{

    /**
     * Constructor for the delete command.
     *
     * @param args The arguments for the command.
     *         Should be an array of length 1,
     *         with the index of the task to be deleted.
     */
    public DeleteCommand(String[] args){
        super(args);
    }

    /**
     * Deletes a task from the task list and updates the storage file.
     * The index of the task to be deleted is specified in the args of
     * the command.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage file.
     */
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
