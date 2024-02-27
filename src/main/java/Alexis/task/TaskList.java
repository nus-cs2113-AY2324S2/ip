package Alexis.task;

import Alexis.console.Ui;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks. It provides methods to add, delete, mark, unmark and print tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int numberOfTasks;

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the task list and increments the count of total tasks.
     *
     * @param task The task to be added to the list.
     */
    private void addToTaskList(Task task) {
        tasks.add(task);
        numberOfTasks++;
        printAddTaskMessage(task);
    }

    /**
     * Adds a task from a saved file to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addToTaskListFromFIle(Task task) {
        tasks.add(task);
        numberOfTasks++;
    }

    private void printAddTaskMessage(Task task) {
        System.out.println(Ui.ADD_MESSAGE);
        System.out.print("\t" + task);
        System.out.printf(Ui.LIST_UPDATE_MESSAGE, numberOfTasks);
    }

    /**
     * Adds a new task to the task list based on the type and description provided.
     *
     * @param taskType The type of task to create.
     * @param input The description of the task.
     */
    public void addTask(TaskType taskType, String input) {
        switch (taskType) {
        case TODO:
        default:
            ToDo toDo = ToDo.getToDo(input);
            addToTaskList(toDo);
            break;
        case DEADLINE:
            Deadline deadline = Deadline.getDeadline(input);
            if (deadline != null) {
                addToTaskList(deadline);
            }
            break;
        case EVENT:
            Event event = Event.getEvent(input);
            if (event != null) {
                addToTaskList(event);
            }
            break;
        }
    }

    /**
     * Marks a task as done based on the task number provided.
     *
     * @param input The string containing the task number to mark as done.
     */
    public void markTask(String input) {
        Integer number = extractInt(input);
        if (isNotValid(number)) {
            return;
        }
        Task task = tasks.get(number - 1);
        task.markAsDone();
        System.out.println(Ui.MARK_DONE_MESSAGE);
        System.out.print("\t");
        printTask(task, -1);
    }

    /**
     * Marks a task as not done based on the task number provided.
     *
     * @param input The string containing the task number to mark as not done.
     */
    public void unmarkTask(String input) {
        Integer number = extractInt(input);
        if (isNotValid(number)) {
            return;
        }
        Task task = tasks.get(number - 1);
        task.markAsNotDone();
        System.out.println(Ui.MARK_UNDONE_MESSAGE);
        System.out.print("\t");
        printTask(task, -1);
    }

    /**
     * Checks if a given number is a valid index for the task list.
     * A number invalid if it is null, less than or equal to zero, or greater than the number of tasks in
     * the list.
     *
     * @param number The index to be validated.
     * @return True if the number is not a valid index, false otherwise.
     */
    private boolean isNotValid(Integer number) {
        if (number == null) {
            System.out.println(Ui.MISSING_TASK_INDEX_MESSAGE);
            return true;
        }
        if (number <= 0 || number > numberOfTasks) {
            System.out.println(Ui.INVALID_TASK_INDEX_MESSAGE);
            return true;
        }
        return false;
    }

    /**
     * Prints all tasks in the task list.
     */
    public void printTasks() {
        int taskIndex = 1;
        System.out.println(Ui.LIST_MESSAGE);
        for (Task task : tasks){
            printTask(task, taskIndex);
            taskIndex++;
        }
    }

    private void printTask(Task task, int index) {
        if (index < 0) {
            System.out.printf(task.toString());
        } else {
            System.out.printf("%d.%s", index, task.toString());
        }
    }

    /**
     * Removes a task from the task list based on its index and decrements the count of total tasks.
     *
     * @param number The index of the task to be removed as reflected on the list of tasks.
     * @return The task that was removed.
     */
    private Task removeTask(int number) {
        Task task = tasks.remove(number - 1);
        numberOfTasks--;
        return task;
    }

    /**
     * Deletes a task from the task list based on the task number provided.
     *
     * @param input The string containing the task number to delete.
     */
    public void deleteFromTaskList(String input) {
        Integer number = extractInt(input);
        if (isNotValid(number)) {
            return;
        }
        Task task = removeTask(number);
        printDeleteTaskMessage(task);
    }

    private void printDeleteTaskMessage(Task task) {
        System.out.println(Ui.REMOVE_MESSAGE);
        System.out.print("\t");
        printTask(task, -1);
        System.out.printf(Ui.LIST_UPDATE_MESSAGE, numberOfTasks);
    }

    /**
     * Extracts and returns the integer from a given string.
     * If the string does not contain any integers or cannot be parsed as an integer, null is returned.
     *
     * @param input The string from which the integer is to be extracted.
     * @return The extracted integer as an Integer object or null if no integer exists.
     */
    private Integer extractInt(String input) {
        try {
            String number = input.replaceAll("[^-0-9]", "");
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
