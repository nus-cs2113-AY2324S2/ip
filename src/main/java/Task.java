public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
        taskCount++;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public void printTask() {
        System.out.println(this);
    }

    public String printDone() {
       return (this.isDone ? "X" : " ");
    }

    public static int getTaskCount() {
        return taskCount;
    }

    @Override
    public String toString() {
        return "[" + this.printDone() + "] " + this.description;
    }
}
