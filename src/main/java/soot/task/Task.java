package soot.task;

import soot.exceptions.SavedFileErrorTypeException;

/**
 * Class Task has 3 members: taskName, isDone and taskType.
 */
// TODO: make class abstract
public class Task {
    public String taskName;
    public boolean isDone;
    public TaskType taskType;

    /**
     * Constructor for Task class.
     * Task Type is not initialised.
     *
     * @param taskName name of the task to be created.
     * @param isDone state of the task whether it has been completed or not.
     */
    public Task(String taskName, Boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Prints the checkbox for the task.
     * If task has been completed, checkbox will contain a "/", else will be an empty space.
     */
    public void printCheckbox() {
        if (isDone) {
            System.out.print("[/]");
        } else {
            System.out.print("[ ]");
        }
    }

    /**
     * Prints all task details for the task with the specified index in the list,
     * in the required format.
     * Note: This index is not the index in the ArrayList.
     *
     * @param index numbering for task in the task list, smallest value being 1.
     */
    public void printTaskInListFormat(int index) {
        System.out.print(index + ".");
        printTaskType();
        printCheckbox();
        System.out.println(" " + taskName);
    }

    /**
     * Task has been completed by user and class member isDone will become true.
     * Chatbot to print out the name of this completed task.
     */
    public void markDone() {
        this.isDone = true;
        System.out.println("good job! this task is marked as done now: ");
        System.out.println(" >> " + this.taskName);
    }

    /**
     * Either task has not been completed by user but was marked done,
     * or if an uncompleted task was accidentally called, chatbot will inform user.
     *
     * Class member isDone for this task is false when method finishes.
     * Chatbot to print out the name of the identified task.
     */
    //TODO: removed printTaskType and printCheckbox due to error: 'void' type not allowed here
    public void markUndone() {
        if (!isDone) {
            System.out.println("hm, this task (" + this.taskName + ") had not been done yet. wrong one?");
            return;
        }
        this.isDone = false;
        System.out.println("This task is now marked undone: ");
        System.out.println("  >> " + this.taskName);
    }

    /**
     * Prints the details of the deleted task and the updated task count.
     */
    public void printRespondWhenDeleteTask() {
        System.out.println("okay, i will remove this task from your list: ");
        System.out.print("  >> ");
        printTaskType();
        printCheckbox();
    }

    /**
     * Prints the details of the added task and the updated task count.
     */
    public void printRespondWhenAddTask() {
        System.out.println("Okay! i've added to ur tasklist:");
        System.out.print(" >> ");
        printTaskType();
        printCheckbox();
    }

    /**
     * Prints the size of the TaskList to indicate the number of tasks in the list,
     * in the required format.
     */
    public void printTaskCount() {
        int taskCount = TaskList.getSize();
        System.out.println("you now have " + taskCount + " tasks left...");
    }

    public void printTaskType() {
        System.out.print("[NA]");
    }

    /**
     * Given a string that contains the letter representing the task type,
     * return the defined enum TaskType type.
     *
     * @param input string that contains one letter representing the task type.
     * @return TaskType of enum TaskType.
     * @throws SavedFileErrorTypeException If input string is not 'T', 'D' or 'E', then it is not a known task type.
     */
    public static TaskType identifyTaskType(String input) throws SavedFileErrorTypeException {
        switch (input) {
        case "T":
            return TaskType.TODO;
        case "D":
            return TaskType.DEADLINE;
        case "E":
            return TaskType.EVENT;
        default:
            throw new SavedFileErrorTypeException();
        }
    }
}