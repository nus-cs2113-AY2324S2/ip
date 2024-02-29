package gary.task;

/**
 * Deadline class deals with task with deadline as the type, performing functions specifically
 * for a deadline task.
 */
public class Deadline extends Task{
    protected String by;
    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.taskType = TaskType.DEADLINE;
        this.by = by;
    }

    /**
     * Take the task and get its deadline by to be returned.
     *
     * @return deadline by of task
     */
    public String getBy() {
        return this.by;
    }


    /**
     * print messages when user adds a task with deadline type.
     *
     * @param todosCount number of tasks in the array list.
     */
    @Override
    public void printAdd(int todosCount) {
        System.out.println("Got it! I've added this task: ");
        System.out.println("  [D][ ] "
                + this.getTaskDescription()
                + "(by: " + this.by + ")");
        System.out.println("Now you have " + todosCount + " tasks in your list.");
    }

    /**
     * print task in a certain format with its details, including the deadline description and by.
     *
     * @param todoCount number of tasks in the array list.
     */
    @Override
    public void printTask(int todoCount) {
        System.out.println((todoCount + 1)
                + ".[D]"
                + "[" + (this.getTaskStatus() ? "X" : " ") + "] "
                + this.getTaskDescription()
                + "(by: " + this.by + ")");
    }
}
