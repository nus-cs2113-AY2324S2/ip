package Kowalski.tasks;

public class Todo extends Task {

    public Todo(String description){
        super(description);
    }


    @Override
    public String textFileInputString(){
        return String.format("T | %s | %s",
                isDone? "X" : "0",
                getDescription().trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
