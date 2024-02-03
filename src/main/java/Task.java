public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
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
    public void mark(Boolean i){
        this.isDone = i;
    }


}
