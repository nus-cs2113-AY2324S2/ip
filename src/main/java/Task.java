public class Task {
    private String taskName;
    private static int numTasks = 0;
    private static final String[] taskList = new String[100];

    public Task(){
        this(null);
    }

    public Task(String taskName){
        if(taskName != null) {
            setTaskName(taskName);
            taskList[numTasks] = taskName;
            numTasks++;
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public static int getNumTasks() {
        return numTasks;
    }

    public static void printTaskList() {
        System.out.println("    Here is your to-do list:");
        for(int i = 0; i < numTasks; i++){
            System.out.println("        " + (i + 1) + ". " + taskList[i]);
        }
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

}
