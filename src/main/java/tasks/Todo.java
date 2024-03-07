package tasks;

public class Todo extends Task {

    public Todo(String description, int taskNum) {
        super(description, taskNum);
        this.type = 'T';
    }
    @Override
    public char getType(){
        return type;
    }
}
