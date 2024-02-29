// Task.java
package task;

public abstract class Task {
    String description;
    String type;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? "X" : " ";
        return "[" + statusIcon + "] " + description;
    }

    public String toStoreString() {
        String statusIcon = isDone ? "1" : "0";
        return type +  " | " + statusIcon + " | " + description;
    }
}


