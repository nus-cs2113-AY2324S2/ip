public abstract class Task {
    private String task;
    private boolean isComplete;
    protected static int number=0;
    public Task(String task)
    {
        this.task=task;
        this.isComplete=false;
        number++;
        System.out.println("Now you have "+number+" tasks in the list.");
    }
    public Task(String task,boolean isComplete)
    {
        this.task=task;
        this.isComplete=isComplete;
        number++;
        System.out.println("Now you have "+number+" tasks in the list.");
    }

    public String getTask() {
        return task;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setComplete(boolean complete) {
        this.isComplete = complete;
    }

    public void markTaskAsDone()
    {
        this.isComplete=true;
    }

    public void unmarkTaskAsDone()
    {
        this.isComplete=false;
    }

    public static void deleteTask() { number--; }

    public String toString() {
        return "[" + (this.isComplete ? "X" : " ") + "] " + this.task;
    }
    public abstract String toFileFormat();
    protected abstract String getTaskType();

}
