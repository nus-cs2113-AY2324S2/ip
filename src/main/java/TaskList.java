import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    public int getLength() {
        return this.tasks.size();
    }

    public void displayTasksList() {
        int taskNumber = 1;
        System.out.println("\tHere are the tasks in your list:");
        for (Task task: tasks) {
            System.out.println("\t" + taskNumber + "." + task.getTaskDetails());
            taskNumber += 1;
        }
        System.out.println();
    }

    public void markTask(int taskIndex) {
        this.tasks.get(taskIndex).markAsDone();
    }

    public void unmarkTask(int taskIndex) {
        this.tasks.get(taskIndex).unmarkAsDone();
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCount += 1;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + tasks.get(taskCount - 1).getTaskDetails());
        System.out.println("\tNow you have " + taskCount
                + (taskCount == 1 ? " task" : " tasks") + " in the list.\n");
    }
}
