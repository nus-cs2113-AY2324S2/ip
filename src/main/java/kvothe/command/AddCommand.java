package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;
import kvothe.task.Deadline;
import kvothe.task.Event;
import kvothe.task.Task;
import kvothe.task.Todo;

/**
 * Represents a command to add a task to the list
 */
public class AddCommand extends Command{

    // The type of task to add: Todo, Event or Deadline
    private String type;

    public AddCommand(String type, String[] args){
        super(args);
        this.type = type;

    }

    /**
     * Adds a task to the list and updates the storage file.
     * Creates a new task based on the type and arguments provided
     * to the command. Add the task to the list and update the storage file.
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage object
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){

        Task newTask = null;

        switch (this.type) {
            case "todo":
                newTask = new Todo(this.args[0]);
                break;
            case "deadline":
                newTask = new Deadline(this.args[0], this.args[1]);
                break;
            case "event":
                newTask = new Event(this.args[0], this.args[1], this.args[2]);
                break;
            default:
                ui.echo("Sorry. I do not support that method.");
                break;
        }

        tasks.add(newTask);
        ui.echo("added: " + tasks.get(tasks.size()-1) + "\n\t\tnow you have " + tasks.size() + " tasks in the list");

        storage.dumpToFile(tasks.getTasks());
    }
}
