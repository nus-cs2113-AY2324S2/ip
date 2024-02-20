package classes;

import classes.Task;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString() ;
    }

    @Override
    public String toFileString(){
        return "T" +  "," + super.toFileString();
    }
}
