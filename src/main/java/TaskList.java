public class TaskList {
    private Task[] tasks;
    private int noOfTasks = 0;
    public TaskList(int taskLength) {
        tasks = new Task[taskLength];
    }

    public void addTask(Task task){
        tasks[noOfTasks] = task;
        noOfTasks++;
    }

    public void checkTask(int taskNumber){
        tasks[taskNumber-1].setTaskStatus(true);
        System.out.printf("      %s %s", tasks[taskNumber-1].getTaskStatus(), tasks[taskNumber-1].getTask());
    }

    public void uncheckTask(int taskNumber){
        tasks[taskNumber-1].setTaskStatus(false);
        System.out.printf("      %s %s", tasks[taskNumber-1].getTaskStatus(), tasks[taskNumber-1].getTask());
    }

    public void listTasks(){
        int taskCount = 0;
        for (Task task: tasks) {
            if (task == null) {
                break;
            }
            taskCount++;
            System.out.printf("     %d. %s %s", taskCount, task.getTaskStatus(),task.getTask());
            System.out.println(" ");
        }
    }


}
