package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    public static int numOfTask = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTask += 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.println(this);
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.println(this);
    }


    public String toString() {
        return " [" + this.getStatusIcon() + "] "
                + this.description ;
    }

}