package OGFTask;

/**
 * Class for all tasks, i.e. to do, deadline and event
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(){
        this.taskName = "";
        this.isDone = false;
    }

    public Task(String task){
        this.taskName = task;
        this.isDone = false;
    }

    public Task(String task, boolean isDone){
        this.taskName = task;
        this.isDone = isDone;
    }
    public String getTaskName() {
        return taskName;
    }

    public boolean getDone(){
        return isDone;
    }

    public void setTaskName(String task){
        this.taskName = task;
    }
    public void setDone(boolean done){
        this.isDone = done;
    }

    /**
     * Converts the task to a String for printing to the user
     * @return String of task
     */
    public String toString(){
        if (this.isDone){
            return("[X] " + this.taskName);
        }
        else{
            return("[ ] " + this.taskName);
        }
    }

    /**
     * Converts the task to a serialised version to store in hardware
     * @return serialized version of task
     */
    public String toSerial(){
        return (this.taskName+","+this.isDone);
    }
}
