package kobot.task;

import kobot.task.Deadline;
import kobot.task.Event;
import kobot.task.Task;
import kobot.task.ToDo;

public class TaskList {
    private static final int MAX_TASK_COUNT = 100;
    private Task[] taskList = new Task[MAX_TASK_COUNT];
    private int taskCount = 0;

    /**
     * Adds a to-do item to the task list.
     *
     * @param description Description of the task.
     */
    public void addToDo(String description) {
        taskList[taskCount] = new ToDo(description);
        System.out.println("Task has been added to list:");
        System.out.println(taskList[taskCount]);
        taskCount++;
    }

    /**
     * Adds a task that needs to be done by a specific date/time to the task list.
     *
     * @param description Description of the task.
     * @param by Date/Time that the task has to be completed by.
     */
    public void addDeadline(String description, String by) {
        taskList[taskCount] = new Deadline(description, by);
        System.out.println("Deadline has been added to list:");
        System.out.println(taskList[taskCount]);
        taskCount++;
    }

    /**
     * Adds a task that starts and ends at a specific date/time.
     *
     * @param description Description of the task.
     * @param from Date/Time that the task starts.
     * @param to Date/Time that the task ends.
     */
    public void addEvent(String description, String from, String to) {
        taskList[taskCount] = new Event(description, from, to);
        System.out.println("Event has been added to list:");
        System.out.println(taskList[taskCount]);
        taskCount++;
    }

    /**
     * Prints the entire task list.
     */
    public void printTaskList() {
        System.out.println("Your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i+1 + ". ");
            System.out.println(taskList[i]);
        }
    }

    /**
     * Marks a specified task as done.
     *
     * @param index Index of the task to mark as done.
     */
    public void markTask(int index) throws NumberFormatException {
        if (index >= taskCount || index < 0) {
            System.out.println("Failed to mark item " + (index + 1) + ". Index out of range.");
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
    public void unmarkTask(int index) throws NumberFormatException {
        if (index >= taskCount || index < 0) {
            System.out.println("Failed to unmark item " + (index + 1) + ". Index out of range.");
            return;
        }
        taskList[index].markAsNotDone();
        System.out.println("Okay, I've marked this task as not done yet: " + taskList[index].getDescription());
    }
}
