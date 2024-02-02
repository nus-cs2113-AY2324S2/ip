public class Task {
    protected String description;
    protected boolean isDone;
    Greet greet = new Greet();
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    public String getDescription() {
        return description;
    }
    public void setIsDone() {
        this.isDone = true;
    }

    public void setIsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
