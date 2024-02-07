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
        System.out.print("[");
        System.out.print(this.printDone());
        System.out.print("] ");
        System.out.println(this.description);
    }

    public String printDone() {
       return (this.isDone ? "X" : " ");
    }

    public static int getTaskCount() {
        return taskCount;
    }
}
