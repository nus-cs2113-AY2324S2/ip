public class Task {
    private final String taskName;
    private boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public void printTask() {
        if (this.isCompleted) {
            System.out.println("[X] " + this.taskName);
        } else {
            System.out.println("[] " + this.taskName);
        }
    }

    public void mark() {
        this.isCompleted = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.print("  ");
        this.printTask();
    }

    public void unmark() {
        this.isCompleted = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.print("  ");
        this.printTask();
    }

}
