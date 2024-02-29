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
     * @param todosCount number of tasks in the array list.
     */
    @Override
    public void printAdd(int todosCount) {
        System.out.println("Got it! I've added this task: ");
        System.out.println("  [T][ ] " + this.getTaskDescription());
        System.out.println("Now you have " + todosCount + " tasks in your list.");
    }

    /**
     * print task in a certain format with its details, including the todo description.
     *
     * @param todoCount number of tasks in the array list.
     */
    @Override
    public void printTask(int todoCount) {
        System.out.println((todoCount + 1)
                + ".[T]"
                + "[" + (this.getTaskStatus() ? "X" : " ") + "] "
                + this.getTaskDescription());
    }
}
