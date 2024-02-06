public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        String icon = isDone ? "X" : " ";
        String status= "[" + icon + "] "+ description;
        return status;
    }

    public void markAsDone() {
        isDone= true; 
    }

    public void markAsUndone() {
        isDone= false;
    }
}
