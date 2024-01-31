public class Task {
   /** Task description*/
    private String task;
    /** Task status */
   private boolean taskIsDone = false;
   public Task(String line){
       task = line;
   }

    /**
     * Returns the task description
     * @return Task description
     */
   public String getTask(){
       return task;
   }

    /**
     * Returns the [X] or [] depending on the status of the task
     * @return [X] or []
     */
   public String getTaskStatus(){
       if(taskIsDone){
           return "[X]";
       }
       return "[ ]";
   }

    /**
     * Sets the task status of the task to complete or incomplete
     * @param taskIsDone Task status
     */
   public void setTaskStatus(boolean taskIsDone){
       this.taskIsDone = taskIsDone;
   }


}
