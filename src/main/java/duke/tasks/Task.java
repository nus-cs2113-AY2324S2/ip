package duke.tasks;

public class Task {
   /** Task description */
    private String task;
    /** Task status */
   private boolean taskIsDone = false;
   public Task(String line){
       task = line;
   }

   @Override
   public String toString(){
       return (taskIsDone? "[X]":"[ ]") + task;
   }

    /**
     * Sets the task status of the task to complete or incomplete
     * @param taskIsDone Task status
     */
   public void setTaskStatus(boolean taskIsDone){
       this.taskIsDone = taskIsDone;
   }

    /**
     * Returns the description of task
     * @return task description
     */
   public String getTask(){
       return task;
   }

    /**
     * Returns the status of the task
     * @return true or false depending on whether the task is done
     */
   public boolean getTaskStatus(){
       return taskIsDone;
   }


}
