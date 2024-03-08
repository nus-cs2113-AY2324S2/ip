import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String task;
    private boolean isComplete;
    public Task(String task)
    {
        this.task=task;
        this.isComplete=false;
    }
    public Task(String task,boolean isComplete)
    {
        this.task=task;
        this.isComplete=isComplete;
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

    public String getDescription()
    {
        return this.task;
    }

    public void markTaskAsDone()
    {
        this.isComplete=true;
    }

    public void unmarkTaskAsDone()
    {
        this.isComplete=false;
    }

    /**
     * transfer task to String
     * @return
     */
    public String toString() {
        return "[" + (this.isComplete ? "X" : " ") + "] " + this.task;
    }

    public abstract String toFileFormat();
    protected abstract String getTaskType();
    public String toStringSave() { return this.toString() ;}


}
