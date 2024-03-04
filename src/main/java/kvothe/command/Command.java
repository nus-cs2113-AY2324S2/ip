package kvothe.command;

import kvothe.Storage;
import kvothe.TaskList;
import kvothe.Ui;

public abstract class Command {

    protected String[] args;

    public Command(String[] args){
        this.args = args;
    }

    public Command(){

    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
    }

    public boolean isExit(){
        return false;
    }
}
