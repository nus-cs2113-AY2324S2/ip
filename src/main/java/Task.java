public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    public String getContent(){
        return description;
    }
    public void changeStatus(boolean status){
        isDone = status;
    }
}
