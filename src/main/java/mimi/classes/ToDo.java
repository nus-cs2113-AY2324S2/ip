package mimi.classes;
import static mimi.helper.Storage.FILE_DELIMINITER;

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
        return "T" +  FILE_DELIMINITER + super.toFileString();
    }
}
