import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    protected String type; // T or D or E

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String toString() {
        return " [" +getStatusIcon() + "] " + description;
    }

    public String toFileString() {
        return null;
    }

    public String toAddString() {
        return null;
    }

}
