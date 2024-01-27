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

    public void listTasks(){
        int taskCount = 0;
        for (Task task: tasks) {
            if (task == null) {
                break;
            }
            taskCount++;
            System.out.printf("     %d. %s", taskCount, task.getTask());
            System.out.println(" ");
        }
    }
}
