public class Task {
    String task;
    boolean complete;
    public Task(String task)
    {
        this.task=task;
        this.complete=false;
    }

    public String getTask() {
        return task;
    }

    public boolean isComplete() {
        return complete;
    }

    public void
    setTask(String task) {
        this.task = task;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    public void markTaskAsDone()
    {
        this.complete=true;
    }
    public void unmarkTaskAsDone()
    {
        this.complete=false;
    }
    public String toString() {
        return "[" + (this.complete ? "X" : " ") + "] " + this.task;
    }

}
