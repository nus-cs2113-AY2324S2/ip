public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " "); //mark task is done with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public String toFileString() {
        // Format: T | 1 | read book
        //         D | 0 | return book | June 6th
        //         E | 0 | project meeting | Aug 6th 2-4pm
        String status = isDone ? "1" : "0";
        return String.format("%s | %s | %s", getTypeSymbol(), status, description);
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected String getTypeSymbol() {
        // This method should be overridden by subclasses (Todo, Deadline, Event)
        return "";
    }
}

