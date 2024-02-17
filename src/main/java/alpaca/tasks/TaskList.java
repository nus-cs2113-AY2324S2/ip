package alpaca.tasks;
public class TaskList {
    protected int listCount;
    protected final Task[] tasks;

    public TaskList() {
        this.listCount = 0;
        this.tasks = new Task[100];
    }

    public int getListCount() {
        return listCount;
    }

    public void add(Task task) {
        tasks[listCount] = task;
        listCount++;
    }

    public boolean isCountValid(int taskIndex) {
        if (taskIndex <= listCount - 1) {
            return true;
        }
        return false;
    }

    public Task getTask(int taskIndex) {
        return tasks[taskIndex];
    }

    public void markTask(int taskIndex) {
        tasks[taskIndex].mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + tasks[taskIndex].toString());
    }

    public void unmarkTask(int taskIndex) {
        tasks[taskIndex].unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + tasks[taskIndex].toString());
    }

    public String listTasks() {
        String taskList = "";
        int taskIndex = 0;
        for (int i = 0; i < listCount; i++) {
            taskIndex++;
            taskList += taskIndex + "." + tasks[i].toString() + "\n";
        }
        return taskList;
    }
}