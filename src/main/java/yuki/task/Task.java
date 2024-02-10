package yuki.task;

public class Task {
    public String description;
    public boolean isDone;
    public String taskType = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Good job.\n[X]" + taskType + " " + description);
    }

    public void markAsUndone() {
        isDone = false;
        System.out.println("Lazy. Just simply lazy.\n[ ]" + taskType + " " + description);
    }

    @Override
    public String toString() {
        return "new todo for you:\n[ ]" + taskType + " " + description;
    }
}
