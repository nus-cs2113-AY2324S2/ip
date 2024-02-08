package task;

import java.util.ArrayList;

public class TaskManager {
    private static final int MAX_TASKS = 128;
    private static int numberOfActiveTasks = 0;
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Retrieves the task associated by its task number.
     *
     * @param taskNumber The task number to retrieve.
     * @return The task associated with the task number. `null` if the task is not found.
     */
    private static Task retrieveTask(int taskNumber) {
        for (Task task : taskList) {
            if (task.getTaskNumber() == taskNumber) {
                return task;
            }
        }
        return null;
    }

    public static void createNewTask(String taskDescription) {
        numberOfActiveTasks += 1;
        Task newTask = new Task(taskDescription, numberOfActiveTasks);
        taskList.add(newTask);
    }

    /**
     * Prints out the whole list of tasks that have been added so far.
     */
    public static void printTaskList() {
        System.out.println("Here are the tasks at hand:");
        for (Task t : taskList) {
            t.printTask();
        }
    }

    public static void markTaskAsDone(int taskNumber) {
        Task taskToMark = retrieveTask(taskNumber);
        if (taskToMark != null) {
            taskToMark.markAsDone();
        }
    }

    public static void markTaskAsUndone(int taskNumber) {
        Task taskToMark = retrieveTask(taskNumber);
        if (taskToMark != null) {
            taskToMark.markAsUndone();
        }
    }
}
