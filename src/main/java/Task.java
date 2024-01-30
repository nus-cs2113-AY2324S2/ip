public class Task {
    private String taskName;
    private static int numTasks = 0;
    private static final String[] taskList = new String[100];
    private static final boolean[] doneList = new boolean[100];

    public Task(){
        this(null);
    }

    public Task(String taskName){
        if(taskName != null) {
            setTaskName(taskName);
            taskList[numTasks] = taskName;
            numTasks++;
            markDone(numTasks - 1, false);
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
            if(doneList[i]){
                System.out.print("        " + (i + 1) + ". " + "[√] ");
            }
            else{
                System.out.print("        " + (i + 1) + ". " + "[ ] ");
            }
            System.out.println(taskList[i]);
        }
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public static void markDone(int index, boolean isDone){
        if(index < numTasks && index >= 0) {
            doneList[index] = isDone;
            if (isDone) {
                System.out.printf("    Task%d is done!\n", index + 1);
            }
        }
        else{
            System.out.printf("    Task%d does not exist.\n", index + 1);
        }
    }

}
