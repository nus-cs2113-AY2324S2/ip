package tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description){
        this.description = description;
        this.isDone = false;
        this.type = ' ';
    }

    public String getDescription(){
        return description;
    }
    public String getStatus(){
        return (isDone ? "1" : "0"); // mark done task w X
    }
    public void markAsDone(){
        isDone = true;
    }
    public void markAsNotDone(){
        isDone = false;
    }
    public char getType(){
        return type;
    }

}
