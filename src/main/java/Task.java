public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t [" + this.getStatusIcon() + "] " + this.description + "\n");
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t [" + this.getStatusIcon() + "] " + this.description + "\n");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}