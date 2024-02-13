public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + getTaskDetails() + "\n");
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + getTaskDetails() + "\n");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTaskDetails() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}