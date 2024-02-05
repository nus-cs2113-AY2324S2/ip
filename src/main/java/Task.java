public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public String getStatus(){
        return (isDone ? "X" : " "); // mark done task w X
    }
    public void markAsDone(){
        isDone = true;
    }
    public void markAsNotDone(){
        isDone = false;
    }

}
