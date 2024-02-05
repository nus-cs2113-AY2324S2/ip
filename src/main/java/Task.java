public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X": " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markTask() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[X] " + description);
    }

    public void unmarkTask() {
        isDone = false;
        System.out.println("Ok, I've marked this task as not done yet: ");
        System.out.println("[] " + description);
    }
}
