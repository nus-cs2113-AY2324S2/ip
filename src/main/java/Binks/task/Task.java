package Binks.task;

public class Task {
    private String taskDescription;
    private boolean isDone;
    public Task(String description){
        this.taskDescription = description;
        this.isDone = false;
    }

    /**
     * Changes the boolean variable isDone to true to show that task is done.
     */
    public void markTaskAsDone(){
        isDone = true;
    }

    /**
     * Changes the boolean variable isDone to false to show that task is not done.
     */
    public void unmarkTaskAsDone(){
        isDone = false;
    }

    /**
     * Returns true if the task is done and false if the task is not done
     */
    public boolean isDone(){

        return isDone;
    }

    @Override
    public String toString(){
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + taskDescription;
    }
}
