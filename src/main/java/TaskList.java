public class TaskList {
    private Task[] taskList = new Task[100];
    private int taskCount = 0;

    public void addTask(String description) {
        System.out.println("Item has been added to list: " + description);
        taskList[taskCount] = new Task(description);
        taskCount++;
    }

    public void printTaskList() {
        System.out.println("Your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i+1) + ". " + taskList[i].getDescription());
        }
    }
}
