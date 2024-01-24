public class Task {
    private final String task;
    private boolean isCompleted;

    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    public void printTask() {
        if (this.isCompleted) {
            System.out.println("[X] " + this.task );
        } else {
            System.out.println("[] " + this.task );
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
