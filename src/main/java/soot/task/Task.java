package soot.task;

import soot.exceptions.SavedFileErrorTypeException;

/**
 * Class Task has 3 members: taskName, isDone and taskType.
 */
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
     * Prints a checkbox for the task.
     * If the task has been completed, the checkbox will contain a "/", else will be an empty space.
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
     * Note: This index is different from the index of this task in the Array List.
     *
     * @param taskIndexInList numbering for task in the task list, it can be a value from 1.
     */
    public void printTaskInListFormat(int taskIndexInList) {
        System.out.print(taskIndexInList + ".");
        printTaskType();
        printCheckbox();
        System.out.println(" " + taskName);
    }

    /**
     * Mark task as completed by user and class member isDone will become true.
     * Chatbot to print out the name of this completed task.
     */
    public void markTaskDone() {
        this.isDone = true;
        System.out.println("good job! this task is marked as done now: ");
        System.out.println(" >> " + this.taskName);
    }

    /**
     * Mark task as not completed and class member isDone for this task is false when method finishes.
     * Either task has not been completed by user but was marked done,
     * or if an uncompleted task was accidentally called, chatbot will inform user.
     *
     * Chatbot to print out the name of the identified task.
     */
    public void markTaskUndone() {
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

    public void printTaskType() {
        System.out.print("[NA]");
    }

    /**
     * Returns the defined enum TaskType of a given string that contains the letter representing the task type.
     *
     *
     * @param inputAlphabet string that contains one letter representing the task type.
     * @return TaskType of enum TaskType.
     * @throws SavedFileErrorTypeException If input string is not 'T', 'D' or 'E', then it is not a known task type.
     */
    public static TaskType identifyTaskType(String inputAlphabet) throws SavedFileErrorTypeException {
        switch (inputAlphabet) {
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