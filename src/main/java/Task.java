public class Task {
    private final String taskName;
    private boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.taskName;
        }
        return "[] " + this.taskName;
    }

    public void mark() {
        this.isCompleted = true;
        // Provide feedback to user
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + this);
    }

    public void unmark() {
        this.isCompleted = false;
        // Provide feedback to user
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + this);
    }

}
