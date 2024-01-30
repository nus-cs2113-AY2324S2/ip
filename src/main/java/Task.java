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

    /**
     * Returns the task that is listed in the list.
     */
    public String getTaskDescription() {
        return taskDescription;
    }
}
