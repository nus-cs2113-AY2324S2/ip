public class ToDo extends Task {
   public String taskType = "T";

   public ToDo(String description){
       super(description);
   }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + super.toString();
    }
}
