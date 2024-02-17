package kobot.task;

import java.util.Arrays;

public class TaskList {
    private static final String EMPTY_STRING = "";
    private static final String FILE_DELIMITER = ";";
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_IS_DONE_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;
    private static final int DEADLINE_BY_INDEX = 3;
    private static final int EVENT_FROM_INDEX = 3;
    private static final int EVENT_TO_INDEX = 4;
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
    
    public void loadTask(String line) {
        String[] data = line.split(FILE_DELIMITER);

        try {
            switch (data[TASK_TYPE_INDEX]) {
            case "T":
                taskList[taskCount] = new ToDo(data[TASK_DESCRIPTION_INDEX]
                        , Boolean.parseBoolean(data[TASK_IS_DONE_INDEX]));
                break;
            case "D":
                taskList[taskCount] = new Deadline(data[TASK_DESCRIPTION_INDEX], data[DEADLINE_BY_INDEX]
                        , Boolean.parseBoolean(data[TASK_IS_DONE_INDEX]));
                break;
            case "E":
                taskList[taskCount] = new Event(data[TASK_DESCRIPTION_INDEX], data[EVENT_FROM_INDEX]
                        , data[EVENT_TO_INDEX], Boolean.parseBoolean(data[TASK_IS_DONE_INDEX]));
                break;
            default:
                break;
            }
            taskCount++;
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Invalid entry found in storage. Entry will be ignored.");
        }
    }
    
    public String toStorageFormat() {
        StringBuilder storage = new StringBuilder(EMPTY_STRING);
        
        for (int i = 0; i < taskCount; i++) {
            storage.append(taskList[i].toStorageFormat());
            storage.append("\n");
        }
        
        return String.valueOf(storage);
    }
}
