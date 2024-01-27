public class Task {
    private String[] tasks;
    private int noOfTasks = 0;
    public Task() {
        tasks = new String[100];
    }

    public void addTask(String task){
        tasks[noOfTasks] = task;
        noOfTasks++;
    }

    public void listTasks(){
        int taskCount = 0;
        for (String task: tasks) {
            if (task == null) {
                break;
            }
            taskCount++;
            System.out.printf("     %d. %s", taskCount, task);
            System.out.println(" ");
        }
    }


}
