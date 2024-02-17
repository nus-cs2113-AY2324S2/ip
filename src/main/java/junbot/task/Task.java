package junbot.task;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String getTag(){
        return " ";
    }

    public String getStartDate(){
        return " ";
    }

    public String getEndDate(){
        return " ";
    }

    public void printTask() {
        System.out.print("[" + this.getStatusIcon() + "] " + this.description + "\n");
    }

}
