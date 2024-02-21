package sayo;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); 
    }

    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description; // This basic format will be extended by subclasses
    }

    public static Task fromFileFormat(String fileFormat) throws SayoException {
        throw new SayoException("This method should be called on a subclass, not on the Task class directly.");
    }
    
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
