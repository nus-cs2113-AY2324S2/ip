package yuki.task;

/**
 * Represents a Task, with <code>description</code>, <code>isDone</code>, and <code>taskType</code> fields.
 */
public class Task {
    public String description;
    public boolean isDone;
    public String taskType = "";

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        isDone = true;
        System.out.println("Good job.\n[X]" + taskType + " " + description);
    }

    /**
     * Marks a task as not done.
     */
    public void markAsUndone() {
        isDone = false;
        System.out.println("Lazy. Just simply lazy.\n[ ]" + taskType + " " + description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + taskType + " " + description;
    }
}
