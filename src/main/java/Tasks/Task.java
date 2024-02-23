package Tasks;

public class Task {
    public String description;
    protected boolean isDone;
    private char Badge = 'X';

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public char getBadge() {
        return Badge;
    }
}
