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

    public String toString(){
        if (this.isDone){
            return("[X] " + this.taskName);
        }
        else{
            return("[ ] " + this.taskName);
        }
    }

    public String toSerial(){
        return (this.taskName+","+this.isDone);
    }
}
