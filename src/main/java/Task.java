public class Task {
    private String taskName;
    private boolean isDone;

    public Task(){
        this.taskName = "";
        this.isDone = false;
    }

    public Task(String task){
        this.taskName = task;
        this.isDone = false;
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

    public void printTask(){
        if (this.isDone){
            System.out.println("[X] " + this.taskName);
        }
        else{
            System.out.println("[ ] " + this.taskName);
        }
    }
}
