package vibes.task;

import vibes.task.type.Deadline;
import vibes.task.type.Event;
import vibes.task.type.Task;
import vibes.task.type.Todo;
import vibes.ui.TextUi;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    public static final int DESCRIPTION_INDEX = 0;
    public static final int BY_INDEX = 1;
    public static final int FROM_INDEX = 1;
    public static final int TO_INDEX = 2;

    public ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param loadTasks the list of tasks to be loaded into the TaskList
     */
    public TaskList(ArrayList<Task> loadTasks) {
        this.tasks = loadTasks;
    }

    /**
     * Adds an Event task to the list of tasks.
     *
     * @param parsedInput the parsed input containing information about the event task
     */
    public void addEvent(String[] parsedInput) {
        tasks.add(new Event(parsedInput[DESCRIPTION_INDEX], parsedInput[FROM_INDEX], parsedInput[TO_INDEX]));
    }

    /**
     * Adds a Deadline task to the list of tasks.
     *
     * @param parsedInput the parsed input containing information about the deadline task
     */
    public void addDeadline(String[] parsedInput) {
        tasks.add(new Deadline(parsedInput[DESCRIPTION_INDEX], parsedInput[BY_INDEX]));
    }

    /**
     * Adds a Todo task to the list of tasks.
     *
     * @param description the description of the to-do task
     */
    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber the index of the task to be marked as done
     * @param ui the TextUi object for displaying messages
     */
    public void setAsDone(int taskNumber, TextUi ui) {
        tasks.get(taskNumber).setDone(true);
        ui.showMarkedMessage(tasks, taskNumber);
    }

    /**
     * Marks a task as not done.
     *
     * @param taskNumber the index of the task to be marked as not done
     * @param ui the TextUi object for displaying messages
     */
    public void setAsNotDone(int taskNumber, TextUi ui) {
        tasks.get(taskNumber).setDone(false);
        ui.showUnmarkedMessage(tasks, taskNumber);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param taskNumber the index of the task to be deleted
     * @param ui the TextUi object for displaying messages
     */
    public void deleteTask(int taskNumber, TextUi ui) {
        Task taskToDelete = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        ui.showDeletedMessage(tasks, taskToDelete);
    }
}
