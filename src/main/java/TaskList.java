public class TaskList {
    private Task[] taskList = new Task[100];
    private int taskCount = 0;

    /**
     * Adds a task to the task list.
     *
     * @param description Description of the task.
     */
    public void addTask(String description) {
        System.out.println("Item has been added to list: " + description);
        taskList[taskCount] = new Task(description);
        taskCount++;
    }

    /**
     * Prints the entire task list.
     */
    public void printTaskList() {
        System.out.println("Your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i+1 + ". ");
            System.out.print(taskList[i].getStatusIcon() + " ");
            System.out.println(taskList[i].getDescription());
        }
    }

    /**
     * Marks a specified task as done.
     *
     * @param index Index of the task to mark as done.
     */
    public void markTask(int index) {
        if (index >= taskCount || index < 0) {
            System.out.println("Failed to mark item " + index + ". Index out of range.");
            return;
        }
        taskList[index].markAsDone();
        System.out.println("Nice! I've marked this task as done: " + taskList[index].getDescription());
    }

    /**
     * Marks a specified task as not done.
     *
     * @param index Index of the task to mark as not done.
     */
    public void unmarkTask(int index) {
        if (index >= taskCount || index < 0) {
            System.out.println("Failed to unmark item " + (index + 1) + ". Index out of range.");
            return;
        }
        taskList[index].markAsNotDone();
        System.out.println("Okay, I've marked this task as not done yet: " + taskList[index].getDescription());
    }
}
