package Task;
/**
 * Represents aTask. A basic Class of <code>Task</code> object
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String get_description(){
        return this.description;
    }
    public String toString(){
        return "[" +getStatusIcon()+"] "+get_description();
    }
    public String get_date(){return null;};
    public void mark(Boolean i){
        this.isDone = i;
    }


}
