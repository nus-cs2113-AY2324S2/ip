package soot.task;

/**
 * Class Event is an inherited class of Class Task.
 * It is a type of task with 5 members: taskName, isDone, taskType, startDate and endDate.
 */
public class Event extends Task {
    public String startDate;
    public String endDate;

    /**
     * Constructor for Event class, will return a reference to the
     * created Event object by default.
     * Task Type for this Task will be EVENT.
     *
     * @param taskName name of the Event task to be created.
     * @param isDone state of the Event task whether it has been completed or not.
     * @param startDate date where this Event task will start.
     * @param endDate date where this Event task will end.
     */
    public Event(String taskName, Boolean isDone, String startDate, String endDate) {
        super(taskName, isDone);
        this.taskType = TaskType.EVENT;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Prints the details of the deleted task and the updated task count.
     * Method overrides printRespondWhenDeleteTask() method in parent Task class.
     */
    @Override
    public void printRespondWhenDeleteTask() {
        super.printRespondWhenDeleteTask();
        System.out.println(" " + this.taskName + " (from: " + startDate + " ~~ to: " + endDate + "!)");
        super.printTaskCount();
    }

    /**
     * Prints the details of the added task and the updated task count.
     * Method overrides printRespondWhenAddTask() method in parent Task class.
     */
    @Override
    public void printRespondWhenAddTask() {
        super.printRespondWhenAddTask();
        System.out.println(" " + this.taskName + " (from: " + startDate + " ~~ to: " + endDate + "!)");
        super.printTaskCount();
    }

    /**
     * Prints the task type E to represent the Event class.
     * Method overrides printTaskType() method in parent Task class.
     */
    @Override
    public void printTaskType() {
        System.out.print("[E]");
    }

    /**
     * Prints all task details for the Event task with the specified index in the list,
     * in the required format.
     * Note: This index is not the index in the ArrayList.
     *
     * @param index numbering for task in the task list, smallest value being 1.
     */
    @Override
    public void printTaskInListFormat(int index) {
        super.printTaskInListFormat(index);
        System.out.println("    >> from: " + startDate + " ~~ to: " + endDate);
    }
}