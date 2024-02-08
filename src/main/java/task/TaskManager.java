package task;

import java.util.ArrayList;
import misc.Parser;

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

    public static void createNewTask(String taskDescription, Task.TaskType taskType) {
        if (taskDescription.isEmpty()){
            System.out.println("Invalid Task: Task Description is empty");
            return;
        }
        if (taskType == Task.TaskType.INVALID){
            System.out.println("Invalid Task Type");
            return;
        }

        numberOfActiveTasks += 1;
        Task newTask = null;
        switch (taskType){
        case TODO:
            newTask = new Todo(taskDescription, numberOfActiveTasks);
            System.out.println("Alright. I have added this todo task: ");
            break;
        case DEADLINE:
            taskDescription = Parser.parseDeadlineDescription(taskDescription);
            newTask = new Deadline(taskDescription,numberOfActiveTasks);
            System.out.println("Alright. I have added this deadline: ");
            break;
        case EVENT:
            taskDescription = Parser.parseEventDescription(taskDescription);
            newTask = new Event(taskDescription,numberOfActiveTasks);
            System.out.println("Alright. I have added this event: ");
            break;
        }
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
