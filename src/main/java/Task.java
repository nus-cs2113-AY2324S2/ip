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


}
