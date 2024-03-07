package bobby;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** The list of tasks. */
    public ArrayList<Task> list;
    private final Ui ui = new Ui();

    /**
     * Constructs a new TaskList instance with an empty task list.
     */
    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param description The description of the todo task.
     */
    public void addTodo(String description) {
        list.add(new Todo(description, false));
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public void addDeadline(String description, String by) {
        list.add(new Deadline(description, false, by));
    }

    /**
     * Adds an event task to the task list.
     *
     * @param description The description of the event task.
     * @param by The date/time when the event starts.
     * @param from The date/time when the event ends.
     */
    public void addEvent(String description, String by, String from) {
        list.add(new Event(description, false, by, from));
    }

    /**
     * Deletes a task from the task list.
     *
     * @param entry The index of the task to be deleted.
     */
    public void deleteTask(int entry) {
        list.remove(entry - 1);
    }

    /**
     * Marks a task as done.
     *
     * @param entry The index of the task to be marked as done.
     */
    public void markTask(int entry) {
        list.get(entry - 1).setDone(true);
    }

    /**
     * Marks a task as not done.
     *
     * @param entry The index of the task to be marked as not done.
     */
    public void unmarkTask(int entry) {
        list.get(entry - 1).setDone(false);
    }

    public void findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.showNoMatchMessage();
        } else {
            ui.showMatchMessage();
            ui.showList(matchingTasks);
        }
    }
}
