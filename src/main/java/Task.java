public class Task {
   private String task;
   private boolean taskIsDone = false;
   public Task(String line){
       task = line;
   }

   public String getTask(){
       return task;
   }

   public String getTaskStatus(){
       if(taskIsDone){
           return "[X]";
       }
       return "[ ]";
   }

   public void setTaskStatus(boolean taskIsDone){
       this.taskIsDone = taskIsDone;
   }


}
