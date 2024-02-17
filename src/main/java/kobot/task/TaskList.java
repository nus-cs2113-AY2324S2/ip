package kobot.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int taskCount = 0;

    /**
     * Adds a to-do item to the task list.
     *
     * @param description Description of the task.
     */
    public void addToDo(String description) {
        ToDo newTodo = new ToDo(description);
        taskList.add(newTodo);
        System.out.println("Task has been added to list:");
        System.out.println(newTodo);
        taskCount++;
    }

    /**
     * Adds a task that needs to be done by a specific date/time to the task list.
     *
     * @param description Description of the task.
     * @param by Date/Time that the task has to be completed by.
     */
    public void addDeadline(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        taskList.add(newDeadline);
        System.out.println("Deadline has been added to list:");
        System.out.println(newDeadline);
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
        Event newEvent = new Event(description, from, to);
        taskList.add(newEvent);
        System.out.println("Event has been added to list:");
        System.out.println(newEvent);
        taskCount++;
    }

    /**
     * Prints the entire task list.
     */
    public void printTaskList() {
        System.out.println("Your list:");
        
        int index = 1;
        for (Task task : taskList) {
            System.out.print(index + ". ");
            System.out.println(task);
            index++;
        }
    }

    /**
     * Marks a specified task as done.
     *
     * @param index Index of the task to mark as done.
     */
    public void markTask(int index) throws NumberFormatException, IndexOutOfBoundsException {
        Task task = taskList.get(index);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: " + task.getDescription());
    }

    /**
     * Marks a specified task as not done.
     *
     * @param index Index of the task to mark as not done.
     */
    public void unmarkTask(int index) throws NumberFormatException, IndexOutOfBoundsException {
        Task task = taskList.get(index);
        task.markAsNotDone();
        System.out.println("Okay, I've marked this task as not done yet: " + task.getDescription());
    }

    /**
     * Deletes a specified task from task list.
     *
     * @param index Index of the task to delete.
     */
    public void deleteTask(int index) throws NumberFormatException, IndexOutOfBoundsException {
        Task task = taskList.get(index);
        taskList.remove(index);
        System.out.println("Item has been deleted: " + task.getDescription());
    }
}
