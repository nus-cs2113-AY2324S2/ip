package tasks;

public class Todo extends Task{

    public Todo(String description){
        super(description);
        this.type = 'T';
    }

    public char getType(){
        return this.type;
    }


}
