package bossman.task;

import bossman.ui.Ui;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class manages a list of tasks.
 * It provides methods to add tasks, remove tasks, find tasks,
 * mark tasks as done, unmark tasks, and print tasks.
 */
public class TaskList {
    private final List<Task> TASKS;


    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        TASKS.add(task);
    }

    /**
     * Finds and print tasks in the task list containing a specified description.
     *
     * @param taskDescription the description to search for in tasks
     */
    public void findTask(String taskDescription) {
        Ui.printMessageNoSepNewLine("Matching tasks:");

        int counter = 1;

        for (Task t: TASKS) {
            if (t.getTask().contains(taskDescription)) {
                Ui.printMessageNoSepSameLine(counter + ". ");
                t.printTask();
                counter += 1;
                Ui.printMessageNoSepNewLine("");
            }
        }

        Ui.printSep();
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskId the ID of the task to be removed
     * @throws IndexOutOfBoundsException if the task ID is invalid
     */
    public void removeTask(int taskId) throws IndexOutOfBoundsException {
        if (isValidTask(taskId)) {
            Task toDeleteTask = TASKS.get(taskId - 1);

            Ui.printMessageNoSepSameLine("Removed task: ");
            toDeleteTask.printTask();
            Ui.printNewLineWithSep();

            TASKS.remove(taskId - 1);
        } else {
            Ui.printMessageWithSepNewLine("No such task");
        }
    }

    public List<Task> getTasks() {
        return TASKS;
    }

    /**
     * Prints all tasks in the task list and number them in order.
     */
    public void printTasks() {
        Ui.printMessageNoSepNewLine("Todo List:");

        int counter = 1;

        for (Task t: TASKS) {
            Ui.printMessageNoSepSameLine(counter + ". ");
            t.printTask();
            counter += 1;
            Ui.printMessageNoSepNewLine("");
        }

        Ui.printSep();
    }

    /**
     * Marks a task as done.
     *
     * @param taskId the ID of the task to be marked as done
     */
    public void markTask(int taskId)  {
        if (isValidTask(taskId)) {
            this.TASKS.get(taskId - 1).setMark();
            Ui.printMessageNoSepNewLine("Nice! I've marked this task as done:");
            echo(taskId);
        } else {
            Ui.printMessageWithSepNewLine("Selected index out of range");
        }
    }

    /**
     * Unmarks a task as done.
     *
     * @param taskId the ID of the task to be marked as not done
     */
    public void unmarkTask(int taskId) {
        if (isValidTask(taskId)) {
            this.TASKS.get(taskId - 1).setUnmark();
            Ui.printMessageNoSepNewLine("OK, I've marked this task as not done yet:");
            echo(taskId);
        } else {
            Ui.printMessageWithSepNewLine("Selected index out of range");
        }
    }

    /**
     * Prints the task at the specified index.
     *
     * @param taskId the ID of the task to be printed
     */
    private void echo(int taskId) {
        this.TASKS.get(taskId - 1).printTask();
        Ui.printNewLineWithSep();
    }

    /**
     * Checks if a task ID is valid.
     *
     * @param taskId the ID of the task to be validated
     * @return true if the task ID is valid, false otherwise
     */
    public boolean isValidTask(int taskId) {
        return taskId > 0 && taskId <= TASKS.size();
    }

    public int getTaskListSize() {
        return TASKS.size();
    }
}


