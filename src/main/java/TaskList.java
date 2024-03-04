import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * TaskList class responsible for all the methods relating to modifying the taskList.
 *
 * @param tasks Task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Returns the list of tasks */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks the task specified as either done or not done.
     *
     * @param key Command keyed in by user input.
     * @param index Index of the task.
     * @return Task marked/unmarked.
     * @throws IndexOutOfBoundsException If the task doesn't exist.
     * @throws TaskMarkerException If the task is already marked/unmarked.
     */
    public Task markTask(String key, int index) throws Exception {
        // Marks or unmarks tasks
        Task t;
        try {
            t = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("That task doesn't exist...");
        }
        if (t.isDone) {
            if (key.equalsIgnoreCase("mark")) { // Catch if the task is already marked
                throw new TaskMarkerException("Task " + (index + 1) + " is already marked done");
            }
        } else {
            if (key.equalsIgnoreCase("unmark")) { // Catch if the task is already unmarked
                throw new TaskMarkerException("Task " + (index + 1) + " is already unmarked");
            }
        }
        t.markDone();
        return t;
    }

    /**
     * Creates an event task and adds it to the list.
     *
     * @param title Title of the task.
     * @param start Start date of the task.
     * @param end End date of the task.
     * @return New event task created.
     */
    public Task createEvent(String title, LocalDateTime start, LocalDateTime end) {
        // Creates new event
        Task task;
        task = new Event(title, start, end, "E");
        tasks.add(task);
        return task;
    }

    /**
     * Creates a deadline task and adds it to the list.
     *
     * @param title Title of the task.
     * @param dueDate Due date of the task.
     * @return New Deadline task created.
     */
    public Task createDeadline(String title, LocalDateTime dueDate) {
        // Creates new deadline task
        Task task;
        task = new Deadline(title, dueDate, "D");
        tasks.add(task);
        return task;
    }

    /**
     * Creates a to do task and adds it to the list.
     *
     * @param title Title of the task.
     * @return New to do task created.
     */
    public Task createTodo(String title) {
        // Creates new task to do
        Task task;
        task = new Todo(title, "T");
        tasks.add(task);
        return task;
    }

    /**
     * Deletes the task from the task list based off of the index.
     *
     * @param index Index of the task to delete.
     * @return Task deleted.
     * @throws IndexOutOfBoundsException If task number keyed in by user is not in the list.
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        // Deletes task from the list
        Task task;
        try {
            task = tasks.get(index);
            tasks.remove(index);
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("I can't delete a task that isn't there...");
        }
        return task;
    }

    /**
     * Finds and filters the list of task based off of the date keyed in by user inputs.
     *
     * @param date Date to file the list by.
     * @return The filtered List.
     */
    public ArrayList<Task> findByDate(LocalDate date) throws NullPointerException{
        // Finds the tasks in the list that matches date
        ArrayList<Task> filteredList = new ArrayList<>();
        try {
            for(Task task : this.tasks) {
                if (task.getEnd() == null) {
                    continue;
                }
                if (task.getEnd().toLocalDate().isEqual(date) || task.getEnd().toLocalDate().isBefore(date)) {
                    filteredList.add(task);
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("You have no tasks due by this date");
        }
        return filteredList;
    }

    /**
     * Finds and filters the list of task based off of the keyword keyed in by user inputs.
     *
     * @param keyword Keyword to file the list by.
     * @return The filtered List.
     */
    public ArrayList<Task> findFromTitle(String keyword) {
        // Finds the tasks in the list that matches title
        Pattern pattern = Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE);

        return (ArrayList<Task>)tasks.stream().filter(task -> pattern.matcher(task.task).find()).collect(Collectors.toList());
    }
}
