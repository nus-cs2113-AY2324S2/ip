public class TaskManager {
    private Task[] tasks;
    private int taskCount;

    private void printList() {
        System.out.println("______________________________________________________________\n"
                + " Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
        System.out.println("_____________________________________________________________");
    }

    private void markTask(int index, boolean isDone) {
        if (isDone) {
            System.out.println("______________________________________________________________\n"
                    + " Good job! I've marked this task as done:\n"
                    + " [X] " + tasks[index].getDescription() + "\n"
                    + "_____________________________________________________________");
        } else {
            System.out.println("______________________________________________________________\n"
                    + " Okay! I've marked this task as not done yet:\n"
                    + " [ ] " + tasks[index].getDescription() + "\n"
                    + "_____________________________________________________________");
        }
    }

    private void addTask(Task task) {
        tasks[taskCount] = task;
        System.out.println("______________________________________________________________\n"
                + " Got it. I've added this task to your list:\n"
                + "   [ ] " + task.getDescription() + "\n"
                + "_____________________________________________________________");
    }

    public TaskManager() {
        tasks = new Task[100];
        taskCount = 0;
    }
}
