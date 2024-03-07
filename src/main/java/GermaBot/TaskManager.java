package GermaBot;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.util.ArrayList;

/**
 * Manages tasks in the GermaBot application.
 */
public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
    }

    /**
     * Marks a task as done.
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param idx The index of the task to mark as done.
     */
    public void markTaskDone(ArrayList<Task> toDoList, int idx) {
        toDoList.get(idx).setDone(true);
    }

    /**
     * Marks a task as undone.
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param idx The index of the task to mark as undone.
     */
    public void markTaskUndone(ArrayList<Task> toDoList, int idx) {
        toDoList.get(idx).setDone(false);
    }

    /**
     * Adds a todo task to the list of tasks.
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param toDoTask The description of the todo task.
     */
    public void addTodo(ArrayList<Task> toDoList, String toDoTask) {
        toDoList.add(new ToDo(toDoTask));
    }

    /**
     * Adds a deadline task to the list of tasks.
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param description The description of the deadline task.
     * @param idxOfEndDate The index of the end date in the String array of the description.
     * @param date The end date of the deadline task.
     */
    public void addDeadline(ArrayList<Task> toDoList, String description, int idxOfEndDate, String date) {
        toDoList.add(new Deadline(description.substring(0, idxOfEndDate - 1), date));
    }

    /**
     * Adds an event task to the list of tasks.
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param description The description of the event task.
     * @param idxOfFrom The index of the from date in the String array of the description.
     * @param startDate The start date of the event task.
     * @param endDate The end date of the event task.
     */
    public void addEvent(ArrayList<Task> toDoList, String description, int idxOfFrom, String startDate, String endDate) {
        toDoList.add(new Event(description.substring(0, idxOfFrom - 1), startDate, endDate));
    }

}
