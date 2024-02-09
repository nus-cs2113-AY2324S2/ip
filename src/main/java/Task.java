public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
public String taskDescription()

{
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public Boolean changeStatus() {
        return this.isDone;
    }


public void setDone() {
        this.isDone = true;
}
 public void setNotDone() {
        this.isDone = false;
 }   //...
}
