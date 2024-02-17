package kyrene.task;

public class Task {
    protected String taskName;
    protected boolean isDone;
    protected String doneSymbol;
    protected String taskType;

    public Task() {
        this(null);
    }

    public Task(String taskName) {
        if(taskName == null) {
            return;
        }
        setTaskName(taskName);
        setDone(false);
        taskType = null;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
        if(isDone){
            doneSymbol = "√";
        }
        else{
            doneSymbol = " ";
        }
    }

    public String getTaskType(){
        return taskType;
    }

    @Override
    public String toString(){
        return String.format("[%s][%s] %s", taskType, doneSymbol, taskName);
    }

    public String format() { return String.format("%b task %s\n", isDone, taskName);}
}
