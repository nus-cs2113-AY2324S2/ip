public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String task) {
        this.description = task;
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String toString() {
        String status = null;
        if (isDone){
            status = "[X] ";
        } else {
            status = "[ ] ";
        }
        return String.format(status + description);
    }
}