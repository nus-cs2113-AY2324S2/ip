package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;
import kvothe.task.Deadline;
import kvothe.task.Event;
import kvothe.task.Task;
import kvothe.task.Todo;

public class AddCommand extends Command{

    private String type;

    public AddCommand(String type, String[] args){
        super(args);
        this.type = type;

    }

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
        ui.echo("added: " + ui.getLine() + "\n\t\tnow you have " + tasks.size() + " tasks in the list");

        storage.dumpToFile(tasks.getTasks());
    }
}
