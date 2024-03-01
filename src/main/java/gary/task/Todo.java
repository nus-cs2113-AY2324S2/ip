package gary.task;

/**
 * Todo class deals with task with todo as the type, performing functions specifically
 * for an todo task.
 */
public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
        this.taskType = TaskType.TODO;
    }

    /**
     * print message when user adds a task with todo type.
     *
     * @param tasksCount number of tasks in the array list.
     */
    @Override
    public void printAdd(int tasksCount) {
        System.out.println("Got it! I've added this task: ");
        System.out.println("  [T][ ] " + this.getTaskDescription());
        System.out.println("Now you have " + tasksCount + " tasks in your list.");
    }

    /**
     * print task in a certain format with its details, including the todo description.
     *
     * @param taskNumber task number in the array list.
     */
    @Override
    public void printTask(int taskNumber) {
        System.out.println((taskNumber + 1)
                + ".[T]"
                + "[" + (this.getTaskStatus() ? "X" : " ") + "] "
                + this.getTaskDescription());
    }
}
