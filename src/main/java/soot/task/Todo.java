package soot.task;

/**
 * Class Todo is an inherited class of Class Task.
 * It is a type of task with 3 members: taskName, isDone and taskType.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo class, will return a reference to the
     * created Todo object by default.
     * Task Type for this Task will be TODO.
     *
     * @param input name of the todo task to be created.
     * @param isDone state of the todo task whether it has been completed or not.
     */
    public Todo(String input, Boolean isDone) {
        super(input, isDone);
        this.taskType = TaskType.TODO;
    }

    /**
     * Prints the details of the deleted task and the updated task count.
     * Method overrides printRespondWhenDeleteTask() method in parent Task class.
     */
    @Override
    public void printRespondWhenDeleteTask() {
        super.printRespondWhenDeleteTask();
        System.out.println(" " + this.taskName);
        printTaskCount();
    }

    /**
     * Prints the details of the added task and the updated task count.
     * Method overrides printRespondWhenAddTask() method in parent Task class.
     */
    @Override
    public void printRespondWhenAddTask() {
        super.printRespondWhenAddTask();
        System.out.println(" " + this.taskName);
        printTaskCount();
    }

    /**
     * Prints the task type T to represent the Todo class.
     * Method overrides printTaskType() method in parent Task class.
     */
    @Override
    public void printTaskType() {
        System.out.print("[T]");
    }
}