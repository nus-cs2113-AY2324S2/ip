/**
 * variables and functions that children classes of tasks inherit
 * children classes in this chatbot include event, deadline and todo
 */
public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String toString(){
        return "[" + getStatusIcon() + "]" + description;
    }
}

