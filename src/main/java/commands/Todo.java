package commands;

public class Todo extends Task {

    public Todo() {
    }

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString(){
        if (isDone){
            return "[T][X] " + name;
        }
        else {
            return "[T][ ] " + name;
        }

    }
}
