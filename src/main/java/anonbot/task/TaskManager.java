package anonbot.task;

import anonbot.exception.InvalidDeleteCommandException;
import anonbot.exception.InvalidMarkCommandException;
import anonbot.exception.InvalidTaskException;

import java.util.ArrayList;

public class TaskManager {
    private static int totalTasksCreated = 0;
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void createNewTask(String taskDescription, Task.TaskType taskType)
            throws InvalidTaskException {
        if (taskDescription.isEmpty()) {
            throw new InvalidTaskException("");
        }
        if (taskType == Task.TaskType.INVALID) {
            System.out.println("Invalid Task Type");
            return;
        }

        // We currently do not check if the deadline and event tasks has the right format i.e. `/by`, `/to`, `/from`.
        // As long as there is a description, we shall accept the new task
        // Todo: Add a default clause to catch any new unhandled task types
        totalTasksCreated += 1;
        Task newTask = null;

        switch (taskType) {
        case TODO:
            newTask = new Todo(taskDescription, totalTasksCreated);
            System.out.println("Alright. I have added this todo task: ");
            break;
        case DEADLINE:
            newTask = new Deadline(taskDescription, totalTasksCreated);
            System.out.println("Alright. I have added this deadline: ");
            break;
        case EVENT:
            newTask = new Event(taskDescription, totalTasksCreated);
            System.out.println("Alright. I have added this event: ");
            break;
        }
        newTask.printTask();
        taskList.add(newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
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

    public static void deleteTask(int taskNumber) throws InvalidDeleteCommandException {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getTaskNumber() == taskNumber) {
                System.out.println("Ok. Task Removed: ");
                task.printTask();
                taskList.remove(i);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                return;
            }
        }
        throw new InvalidDeleteCommandException("delete", Integer.toString(taskNumber));
    }
}
