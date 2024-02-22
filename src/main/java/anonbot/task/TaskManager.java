package anonbot.task;

import anonbot.exception.InvalidCommandException;
import anonbot.exception.InvalidMarkCommandException;
import anonbot.exception.InvalidTaskException;

import java.util.ArrayList;

public class TaskManager {
    private static int numberOfActiveTasks = 0;
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static int getNumberOfActiveTasks() {
        return numberOfActiveTasks;
    }

    public static void setNumberOfActiveTasks(int numActiveTasks) {
        numberOfActiveTasks = numActiveTasks;
    }

    public static Task createTask(String taskDescription, Task.TaskType taskType,
            int taskNumber, boolean isTaskDone) {
        Task newTask = null;

        switch (taskType) {
        case TODO:
            newTask = new Todo(taskDescription, taskNumber);
            break;
        case DEADLINE:
            newTask = new Deadline(taskDescription, taskNumber);
            break;
        case EVENT:
            newTask = new Event(taskDescription, taskNumber);
            break;
        }
        newTask.setTaskStatus(isTaskDone);
        taskList.add(newTask);
        return newTask;
    }

    public static void createNewTask(String taskDescription, Task.TaskType taskType)
            throws InvalidTaskException {
        if (taskDescription.isEmpty()) {
            throw new InvalidTaskException("Invalid Task: Task Description is empty");
        }
        if (taskType == Task.TaskType.INVALID) {
            System.out.println("Invalid Task Type");
            return;
        }

        // We currently do not check if the deadline and event tasks has the right format i.e. `/by`, `/to`, `/from`.
        // As long as there is a description, we shall accept the new task
        // Todo: Add a default clause to catch any new unhandled task types
        numberOfActiveTasks += 1;
        Task newTask = createTask(taskDescription, taskType, numberOfActiveTasks, false);
        newTask.printTask();
        System.out.println("Alright. I have added this task: ");
        System.out.println("Now you have " + numberOfActiveTasks + " tasks in the list.");
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

    public static void markTaskAsDone(int taskNumber) throws InvalidMarkCommandException {
        Task taskToMark = retrieveTask(taskNumber);

        if (taskToMark == null) {
            throw new InvalidMarkCommandException("mark", Integer.toString(taskNumber));
        }

        taskToMark.markAsDone();
    }

    public static void markTaskAsUndone(int taskNumber) throws InvalidMarkCommandException {
        Task taskToMark = retrieveTask(taskNumber);

        if (taskToMark == null) {
            throw new InvalidMarkCommandException("unmark", Integer.toString(taskNumber));
        }

        taskToMark.markAsUndone();
    }
}
