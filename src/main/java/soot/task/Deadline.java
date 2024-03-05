package soot.task;

/**
 * Class Deadline is an inherited class of Class Task.
 * It is a type of task with 4 members: taskName, isDone, taskType and dueDate.
 */
public class Deadline extends Task {
    public String dueDate;

    /**
     * Constructor for Deadline class, will return a reference to the
     * created Deadline object by default.
     * Task Type for this Task will be DEADLINE.
     *
     * @param taskName name of the Deadline task to be created.
     * @param isDone state of the Deadline task whether it has been completed or not.
     * @param dueDate deadline for this particular Deadline task.
     */
    public Deadline(String taskName, Boolean isDone, String dueDate) {
        super(taskName, isDone);
        this.taskType = TaskType.DEADLINE;
        this.dueDate = dueDate;
    }

    /**
     * Prints the details of the deleted task and the updated task count.
     * Method overrides printRespondWhenDeleteTask() method in parent Task class.
     */
    @Override
    public void printRespondWhenDeleteTask() {
        super.printRespondWhenDeleteTask();
        System.out.println(" " + this.taskName + " (by: " + dueDate + "!)");
        super.printTaskCount();
    }

    /**
     * Prints the details of the added task and the updated task count.
     * Method overrides printRespondWhenAddTask() method in parent Task class.
     */
    @Override
    public void printRespondWhenAddTask() {
        super.printRespondWhenAddTask();
        System.out.println(" " + this.taskName + " (by: " + dueDate + "!)");
        super.printTaskCount();
    }

    /**
     * Prints the task type D to represent the Deadline class.
     * Method overrides printTaskType() method in parent Task class.
     */
    @Override
    public void printTaskType() {
        System.out.print("[D]");
    }

    /**
     * Prints all task details for the Deadline task with the specified index in the list,
     * in the required format.
     * Note: This index is not the index in the ArrayList.
     *
     * @param index numbering for task in the task list, smallest value being 1.
     */
    @Override
    public void printTaskInListFormat(int index) {
        super.printTaskInListFormat(index);
        System.out.println("    >> by: " + dueDate + "!!");
    }
}